package com.company;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static  void main(String[] args) throws IOException {
        //1
        String text = "";
        Scanner in = new Scanner(new File("text1.txt"));
        while(in.hasNext())
            text += in.nextLine() + "\n";
        in.close();
        System.out.println(text);
        String inputText = text.replaceAll("[,.!]"," ").replaceAll("\\s+"," ");
        String inputTextM = inputText.toLowerCase();
        String[] textAl = inputTextM.split(" ");
        //System.out.println(Arrays.toString(textAl)); //проверка массива

        List<String> arrList = new ArrayList<>(Arrays.asList(textAl));
        Set<String> newSet = new LinkedHashSet<>(arrList);
        System.out.println(newSet);
        System.out.println();
        System.out.println("1. Количество слов в файле: "+ arrList.size() + ", количество различных слов: " + newSet.size());
        System.out.println();
        //2
        List<String> sortList = new ArrayList<>(newSet);
        Collections.sort(sortList, new MyComparator());
        System.out.println("2. Сортировка различных слов по длине, затем по алфавиту: ");
        System.out.println(sortList);
        System.out.println();
        //3
        HashMap<String, Integer> wordToCount = new HashMap<>();
        for (String word : arrList) {
            if (!wordToCount.containsKey(word)) {
                wordToCount.put(word, 0);
            }
            wordToCount.put(word, wordToCount.get(word) + 1);
        }
        System.out.println("3. Количество повторений слов: ");
        for (String word : wordToCount.keySet()) {
            System.out.print(word + " " + wordToCount.get(word) + "; ");
        }
        System.out.println();
        System.out.println();
        //4
        System.out.println("4. Обратный порядок слов: ");
        Stack  sList = new Stack();
        for (String word : arrList) {
            sList.push(word);
        }
        while (!sList.empty()) {
            System.out.print(sList.pop()+ " ");
        }
        System.out.println();
        System.out.println();
        //5
        System.out.println("5. Итератор для обхода списка в обратном порядке: ");
        ReverseIterator ri = new ReverseIterator(arrList);
        ListIterator<String> li = arrList.listIterator();
        while (ri.hasNext()){
            String s = ri.next().toString();
            if (s.equals("В")){
                ri.remove();
            }
            else
            {
                System.out.print(s + "; ");
            }
        }
        System.out.println();
        System.out.println();
        //6
        System.out.println("6. Вывести строки в порядке заданном пользователем");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите номер строки: ");
        if (scanner.hasNextInt()){
            int num = scanner.nextInt();
            try{
                System.out.println(arrList.get(num-1));
            }
            catch (IndexOutOfBoundsException e){
                System.out.println("нет такой строки");
            }
        }
        else {
            System.out.println("Некорректный номер");
        }

    }
    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String item1, String item2) {
            if (item1.length() == item2.length()) {
                return item1.compareTo(item2);
            }
            else {
                return item1.length() - item2.length();
            }
        }
    }
    public static class ReverseIterator<T> implements Iterator<T>, Iterable<T> {

        private final List<T> list;
        private int position;

        public ReverseIterator(List<T> list) {
            this.list = list;
            this.position = list.size() - 1;
        }

        @Override
        public Iterator<T> iterator() {
            return this;
        }

        @Override
        public boolean hasNext() {
            return position >= 0;
        }

        @Override
        public T next() {
            return list.get(position--);
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}