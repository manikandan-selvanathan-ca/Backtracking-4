import java.util.*;

public class BraceExpansion {

    //Group the strings by curly braces.
    //Backtrack to generate the all the different combinations
    //Recurse call of groups.
    //Each group start from the first character then move on to the next group and repeat the same process.
    //Once we explored all the group, Backtrack and try other characters in the group.
    //TC: K^N 
    //SC: K^N for backtracking recursive call stack
    public String[] expand(String s) {

        if (s == null || s.length() == 0)
            return new String[] {};

        List<List<Character>> groups = groupTheString(s); //Group the string
        List<String> result = backtracking(groups, 0, new StringBuilder(), new ArrayList()); //Backtrack
        String[] resultArray = new String[result.size()];//Conversion from ArrayList to string array
        for (int i = 0; i < result.size(); i++) {
            resultArray[i] = result.get(i);
        }
        Arrays.sort(resultArray);
        return resultArray;

    }

    private List<List<Character>> groupTheString(String s) {

        List<List<Character>> groups = new ArrayList();

        int i = 0;
        while (i < s.length()) {
            List<Character> group = new ArrayList();
            if (s.charAt(i) == '{') {
                i++;
                while (s.charAt(i) != '}') {
                    if (s.charAt(i) != ',') {
                        group.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                group.add(s.charAt(i));
            }
            groups.add(group);
            i++;
        }
        return groups;
    }

    private List<String> backtracking(List<List<Character>> groups, int index, StringBuilder sb, List<String> result) {
        if (index == groups.size()) {
            result.add(sb.toString());
            return result;
        }

        List<Character> group = groups.get(index);
        for (int i = 0; i < group.size(); i++) {
            sb.append(group.get(i));
            backtracking(groups, index + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        return result;
    }

    public static void main(String[] args) {
        BraceExpansion braceExpansion = new BraceExpansion();
        String[] result = braceExpansion.expand("{a,b}c{d,e}f");
        System.out.println("The result: "+result);
    }
}