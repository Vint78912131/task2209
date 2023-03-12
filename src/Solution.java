import java.io.*;
import java.util.*;

/*
Составить цепочку слов
В методе main считай с консоли имя файла, который содержит слова, разделенные пробелом.
В методе getLine используя StringBuilder расставь все слова в таком порядке, чтобы последняя
буква данного слова совпадала с первой буквой следующего не учитывая регистр.
Каждое слово должно участвовать 1 раз.
Метод getLine должен возвращать любой вариант.
Слова разделять пробелом.
Вывести полученную строку на экран.
Пример тела входного файла:
Киев Нью-Йорк Амстердам Вена Мельбурн
Результат:
Амстердам Мельбурн Нью-Йорк Киев Вена
Требования:
1.Метод main должен считывать имя файла с клавиатуры.
2.В методе getLine должен быть использован StringBuilder
3.Метод getLine должен возвращать пустую строку(пустой StringBuilder) в случае если ему не были
переданы параметры(слова).
4.Все слова переданные в метод getLine должны быть включены в результирующую строку, если это возможно.
5.Вывод на экран должен соответствовать условию задачи.
*/

public class Solution {
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        BufferedReader buff = new BufferedReader(new FileReader(fileName));

        ArrayList<String> allWords = new ArrayList<>();
        while (buff.ready()) {
            String line = buff.readLine();
            String [] words = line.split(" ");
            allWords.addAll(Arrays.asList(words));
        }

        reader.close();
        buff.close();


        StringBuilder result = getLine(allWords);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(ArrayList<String> words) {
        StringBuilder result = new StringBuilder();
        if (words == null || words.size() == 0) return result;
        if (words.size()==1 || words.get(0).equals("")) return result.append(words.get(0));

        while (words.remove("")){
            words.remove("");
        }
        while (checkEnd(words)) {
            Collections.shuffle(words);
        }
        for (String word: words){
            result.append(word).append(" ");
        }
        result.deleteCharAt(result.length()-1);
        return result;
    }

    public static boolean checkEnd(ArrayList<String> words) {
        for (int i = 0; i < words.size() - 1; i++) {
            String firstWord = words.get(i).toLowerCase();
            String secondWord = words.get(i + 1).toLowerCase();
            if (firstWord.charAt(firstWord.length() - 1) != secondWord.charAt(0)) return true;
        }
        return false;
    }
}


/*
принятое решение
public class Solution {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(reader.readLine())))) {
            while (fileReader.ready()) {
                list.add(fileReader.readLine());
            }
        } catch (IOException ignored) {
        }

        List<String> resultList = new ArrayList<>();
        for (String line : list) {
            StringTokenizer tokenizer = new StringTokenizer(line);
            while (tokenizer.hasMoreTokens()) {
                resultList.add(tokenizer.nextToken());
            }
        }

        StringBuilder result = getLine(getWords(resultList));
        System.out.println(result.toString());
    }

    private static String[] getWords(List<String> list) {
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    private static boolean isTheSameChars(String firstWord, String secondWord) {
        if (firstWord.endsWith(" ")) {
            firstWord = firstWord.substring(0, firstWord.length() - 1);
        }
        return firstWord.isEmpty() || (secondWord != null &&
                Character.toUpperCase(firstWord.charAt(firstWord.length() - 1)) == Character.toUpperCase(secondWord.charAt(0)));
    }

    private static <T> T getLastElement(List<? extends T> list) {
        return list.get(list.size() - 1);
    }


    public static StringBuilder getLine(String... words) {
        StringBuilder builder = new StringBuilder();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            list.add(i);
            if (findSolutions(list, words)) {
                for (Integer integer : list) {
                    builder.append(words[integer]);
                    builder.append(" ");
                }
                return builder;
            }
            list.remove(Integer.valueOf(i));
        }

        return builder;
    }

    private static boolean findSolutions(List<Integer> list, String... words) {
        if (list.size() == words.length) {
            return true;
        }
        for (int i = 0; i < words.length; i++) {
            if (isValid(list, words[i], words)) {
                list.add(i);
                if (findSolutions(list, words)) {
                    return true;
                }
                list.remove(Integer.valueOf(i));
            }
        }
        return false;
    }

    private static boolean isValid(List<Integer> list, String word, String... words) {
        for (Integer integer : list) {
            if (words[integer].equals(word)) {
                return false;
            }
        }
        return isTheSameChars(words[getLastElement(list)], word);
    }
 */