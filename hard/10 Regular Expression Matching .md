# 10. Regular Expression Matching

Given an input string `s` and a pattern `p`, implement regular expression matching with support for `'.'` and `'*'` where:

- `'.'` Matches any single character.
- `'*'` Matches zero or more of the preceding element.

The matching should cover the **entire** input string (not partial).

**Example 1:**

```
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

```

**Example 2:**

```
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

```

**Example 3:**

```
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

```

**Constraints:**

- `1 <= s.length <= 20`
- `1 <= p.length <= 20`
- `s` contains only lowercase English letters.
- `p` contains only lowercase English letters, `'.'`, and `'*'`.
- It is guaranteed for each appearance of the character `'*'`, there will be a previous valid character to match.

## Approche 1 ( wrong approche  )

1. we will put one pointer to String and one pointer to patten 
2. we will match char from pointer to String 
    1. if patten[ i ] == ‘.’
        1. we will mach any one char from String and increase both pointer 
        2. if there is nor char left in string we will return false
    2. if patten[ i ] == ‘ any alphabet ‘ ( here we need to chek char should not ‘*’ in pattern  )
        1. we will mach this alphabet with string char 
        2. if both match we will increase both pointers
    3. if patten[ i ] == ‘ any alphabet ‘ and next char is * 
        1. we will skip all char which is same as pattern[i] than increse pointer in pattern and after ‘*’ char
3. there is one issue what is sub paten  is something like “ a*a ”  // “ a*. ”( “b” “b*a*b” ) and string is “aa” our code will return false which this is correct
4. to handle this we will maintain one array of size String where we add char from patten  by which string char mach ex. c is match by  C* we will add ’*’, if by ‘ . ’ we will add ‘ . ’ , if by alphabet we will add ‘ alphabet ‘ 
    1. so  s = “aa” p=”a*a” where our patten pointer is on last ‘ a ’ that time our string pointer outside of string ( or at invalid char ) so we check last most chat is ‘ a ’ ? if yes than we will check is it match by ‘*’ if yes we will replace this ‘*’ by a and increase oure patten pointer but nor string pointer 
5. there is still one issue ex s = “aa” p=”a*aa” in this case 
    1. “a*” will mach entire string and our mach String (ms) = “**”
    2. when p[2] which ‘a’ come we will check and replace last char in match string “*a”
    3. whem p[3] which ‘a’ come we will check last place in ms which not ‘*’ so we return false 
6. solution of this issue
    1. when p[2] rahter than chan last * but will change first ‘ * ’ in  continuas sequnce 
    2. so our chaged ms is “a*”
    3. p[3] come will mach char from string and when it will check in ms ther is ‘ * ’ available so ms will chage to ‘ aa ’ and we will return true;
7. there is one more issue what if s = “aab” p = “a* . b”
    1. it will increase complexity too much so let’s think about other approch 

## issue

1. we can decide ‘*’ symbole mach how many char without knowing upcomming char 

## Approche 2

1. we will create recursion function match(s,sp,p,pp)
2. which will return true is match return false if not 
3. assume that both pointer is in range
    1. if p[pp] == ‘ . ’ will call function by increase both pointer 
    2. if p[pp] == ‘alphabet’ (not follow by ‘*’) we will mach char we not same return false if same in both pointer and call function 
    3. if if p[pp] == ‘alphabet’ ( follow by ‘*’) this thing we need to take care (we do’t know how many char so match by this patten so we will call two funtion by OR operater first by match this char and second by not matching char )
    4. funtion call : if( s[sp] ≠ p[pp]) return match( s, sp, p, pp+2 )
    5. if( s[sp] = p[pp] ) return match(s, sp, p ,pp+2) || match(s, sp+1, p, pp)(this will handdle all cases)
4. if any pointer out side of rang we are handle this in beginning 
    1. both pointer out of range return TRUE
    2. pp out of range but sp not return FALSE(some porsen of string not matched )
    3. sp out of rage but pp not ( in this case we need to handel (’ * ’) patten because it can match 0 char also  )
        1. if( pp ≠ p.length-1 && p[pp+1] == ‘*’ ) return match( s,sp,p,pp+2 );
        2. else return false;
5. if “.*” come which we can match any chars any in any rage 

## Code

```java
class Solution {
    public boolean isMatch(String s, String p) {
        return match( s.toCharArray(), 0, p.toCharArray(), 0 );
    }
    boolean match( char[] s, int sp, char[] p, int pp ){

        if( pp == p.length ){
            if( sp == s.length ){
                return true;
            }else{
                return false;
            }
        }else{
            if( sp == s.length ){
                if( pp != p.length-1 && p[pp+1] == '*' ){
                    return match( s,sp,p,pp+2 );
                }else{
                    return false;
                }
            }
        }

        if( p[pp] == '.' ){

            if( pp != p.length-1 && p[pp+1] == '*' ){
                return match(s, sp, p, pp+2) || match(s, sp+1, p, pp);
            }else {
                return match( s, sp+1, p, pp+1 );
            }
        }else  if( pp != p.length-1 && p[pp+1] == '*' ){
            if( s[sp] == p[pp] ){
                return match(s, sp, p, pp+2) || match(s, sp+1, p, pp);
            }else{
                return match( s,sp,p, pp+2 );
            }
        }else{
            if( s[sp] == p[pp] ){
                return match( s, sp+1, p, pp+1 );
            }else{
                return false;
            }
        }
       
    }
}

```