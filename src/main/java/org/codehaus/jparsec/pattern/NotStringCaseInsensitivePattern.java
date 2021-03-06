package org.codehaus.jparsec.pattern;

import static org.codehaus.jparsec.pattern.StringCaseInsensitivePattern.compareIgnoreCase;

class NotStringCaseInsensitivePattern extends Pattern {

  private final String string;

  public NotStringCaseInsensitivePattern(String string) {
    this.string = string;
  }

  @Override
  public Pattern derive(char c) {
    if(string.length() > 0 && !compareIgnoreCase(c, string.charAt(0)))
      return Patterns.notStringCaseInsensitive(string.substring(1));
    
    return Patterns.NEVER;
  }

  @Override
  public int match(CharSequence src, int begin, int end) {
    if (begin >= end)
      return MISMATCH;
    if (StringCaseInsensitivePattern.matchStringCaseInsensitive(string, src, begin, end) == Pattern.MISMATCH)
      return 1;
    else
      return MISMATCH;
  }

  public String toString(){
    return "!(" + string.toUpperCase() + ")";
  }
}
