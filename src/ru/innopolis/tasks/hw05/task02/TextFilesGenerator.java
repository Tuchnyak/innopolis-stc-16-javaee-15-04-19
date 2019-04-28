package ru.innopolis.tasks.hw05.task02;

import java.io.*;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Создать генератор текстовых файлов, работающий по следующим правилам:
 * <p>
 * Предложение состоит из 1-n1-15 слов. В предложении после произвольных слов могут находиться запятые.
 * Слово состоит из 1-n2-15 латинских букв
 * Слова разделены одним пробелом
 * Предложение начинается с заглавной буквы
 * Предложение заканчивается (.|!|?)+" "
 * Текст состоит из абзацев. в одном абзаце 1-n3-20 предложений. В конце абзаца стоит разрыв строки
 * и перенос каретки.
 * Есть массив слов 1-n4-1000. Есть вероятность probability вхождения одного из слов этого массива
 * в следующее предложение (1/probability).
 * <p>
 * Необходимо написать метод getFiles(String path, int n, int size, String[] words, int probability),
 * который создаст n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
 *
 * @author Georgii Shchennikov
 */
public class TextFilesGenerator {

    private static final String DESTINATION_PATH = "./src/ru/innopolis/tasks/hw05/task02/output_files/";
    private static final String SOURCE_PATH = "./src/ru/innopolis/tasks/hw05/task02/thousand.txt";
    private static final char[] LETTERS;
    private static final char[] SIGNS;
    private static final String[] THOUSAND;
    private static final SecureRandom RANDOM;

    private static int probabilityStatic;

    static {
        LETTERS = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        SIGNS = new char[]{'.', '!', '?'};
        THOUSAND = getThousandWords();
        RANDOM = new SecureRandom();
        probabilityStatic = 0;
    }


    public static void main(String[] args) {

        getFiles(DESTINATION_PATH, 3, 1333, THOUSAND, 3);

    }


    /**
     * Генерирует слова длиной от 1 до 15 из случайных латинских букв
     * или берёт одно из 1000 слов в зависимости от значения вероятности
     *
     * @return слово из случайных латинских букв
     */
    private static String genWord() {

        boolean useThousand = false;

        if (probabilityStatic > 0) {
            double border = 1d / probabilityStatic;
            double randomValue = RANDOM.nextDouble();
            if (randomValue <= border)
                useThousand = true;
        }

        if (!useThousand) {
            int min = 1;
            int max = 15;

            int length = min + RANDOM.nextInt(max);

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < length; i++) {
                sb.append(LETTERS[RANDOM.nextInt(LETTERS.length)]);
            }

            return sb.toString();
        }

        return THOUSAND[RANDOM.nextInt(THOUSAND.length)];
    }

    /**
     * Генерирует случайное предложение длиной от 1 до 15 слов со случайным знаком на конце.
     *
     * @return строка со случайным предложением
     */
    private static String genSentence() {
        int min = 1;
        int max = 15;

        int sentenceLength = min + RANDOM.nextInt(max);

        String firstWord = genWord();

        if (firstWord.length() > 1) {
            firstWord = firstWord.substring(0, 1).toUpperCase().concat(firstWord.substring(1));
        } else {
            firstWord = firstWord.toUpperCase();
        }

        if (sentenceLength == 1) return firstWord.concat(String.valueOf(getRandomSign())).concat(" ");

        StringBuilder sb = new StringBuilder();

        sb.append(firstWord).append(" ");

        for (int i = 1; i < sentenceLength; i++) {
            sb.append(genWord());
            if (i == sentenceLength - 1)
                sb.append(getRandomSign()).append(" ");
            else
                sb.append(" ");
        }

        return sb.toString();
    }

    /**
     * Генерирует абзац, состоящий из случайных предложений в количестве от 1 до 20
     *
     * @return абзац случайных предложений
     */
    private static String genParagraph() {
        int min = 1;
        int max = 20;
        String endOfParapgraph = "\n\r";

        int paragraphLength = min + RANDOM.nextInt(max);

        if (paragraphLength == 1) {
            return genSentence().concat(endOfParapgraph);
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < paragraphLength; i++) {
            sb.append(genSentence());
            if (i == paragraphLength - 1) sb.append(endOfParapgraph);
        }

        return sb.toString();
    }

    //  n файлов размером size в каталоге path. words - массив слов, probability - вероятность.
    private static boolean getFiles(String path, int numberOfFiles, int sizeOfFile, String[] words, int probability) {

        List<String> filePaths = new ArrayList<>();
        probabilityStatic = probability;

        for (int i = 0; i < numberOfFiles; i++) {
            filePaths.add(path.concat("genFile_").concat(String.valueOf(i)));
        }

        for (int i = 0; i < numberOfFiles; i++) {

            String pathToFile = filePaths.get(i);

            try (FileOutputStream fos = new FileOutputStream(pathToFile)) {

                byte[] byteContent;
                int toFill, filled = 0;
                boolean keepWriting = true;

                while (keepWriting) {

                    byteContent = genParagraph().getBytes();
                    toFill = byteContent.length;

                    if (sizeOfFile - (filled + toFill) < 0) {
                        int dif = sizeOfFile - filled;
                        for (int j = 0; j < dif; j++) {
                            fos.write(byteContent[j]);
                            fos.flush();
                        }
                        keepWriting = false;
                    } else {
                        fos.write(byteContent);
                        fos.flush();
                        if (sizeOfFile - (filled + toFill) == 0)
                            keepWriting = false;
                    }

                    filled += byteContent.length;

                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

        }

        return true;
    }

    /**
     * @return случайный символ из массива SIGNS
     */
    private static char getRandomSign() {
        return SIGNS[RANDOM.nextInt(SIGNS.length)];
    }

    /**
     * читывание из файла 1000 слов в массив
     *
     * @return массив, заполненый словами из файла
     */
    private static String[] getThousandWords() {

        String[] arr = new String[1000];

        try (BufferedReader reader = new BufferedReader(new FileReader(SOURCE_PATH))) {

            String str = null;
            int i = 0;
            while ((str = reader.readLine()) != null) {
                arr[i] = str;
                i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }

}
