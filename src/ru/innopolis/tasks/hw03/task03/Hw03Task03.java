package ru.innopolis.tasks.hw03.task03;

/**
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы. Функциональность в целом должна сохраниться.
 * При попытке положить Object в MathBox должно создаваться исключение.
 *
 * @author Georgii Shchennikov
 */
public class Hw03Task03 {

    public static void main(String[] args) {

        System.out.println("*** Проверяем старый функционал");

        Number[] numbers = {0f, 1f, 2f, 3f};

        MathBox mathBox = new MathBox(numbers);
        MathBox mathBox1 = new MathBox(numbers);

        System.out.println("Проверяем метод equals()");
        System.out.println(mathBox.equals(mathBox1));
        System.out.println();

        System.out.println("Проверяем метод hashCode()");
        System.out.println(mathBox.hashCode());
        System.out.println(mathBox1.hashCode());
        System.out.println();

        System.out.println("Выводим результат работы метода summator()");
        System.out.println(mathBox.summator());
        System.out.println();

        mathBox.splitter(4);

        System.out.println("Проверяем метод equals() после работы splitter()");
        System.out.println(mathBox.equals(mathBox1));
        System.out.println();

        System.out.println("Проверяем toString()");
        System.out.println(mathBox);
        System.out.println(mathBox1);
        System.out.println();

        mathBox1.integerDeleter(2);
        System.out.println("Выводим объект после работы метода integerDeleter()");
        System.out.println(mathBox1);
        System.out.println();


        System.out.println("*** Проверяем функционал наследования");

        mathBox.addObject(new Object());
        System.out.println(mathBox.summator());

        mathBox1.addObject(1);

    }

}
