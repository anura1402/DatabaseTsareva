package ru.anura.database;

import ru.anura.database.ConnectionDb;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Database {


    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);
        ConnectionDb.Connection();
        ConnectionDb.CreateDB();
        int i = -1;
        while (i != 0) {
            System.out.println("Что вы хотите сделать с таблицей?");
            System.out.println("1 - Добавить запись");
            System.out.println("2 - Вывести таблицу");
            System.out.println("3 - Вывести записи по определенному году");
            System.out.println("4 - Вывести записи по уникальному Id");
            System.out.println("5 - Вывести запись по ее Id и году");
            System.out.println("6 - Изменить запись");
            System.out.println("7 - Удалить запись");
            System.out.println("0 - Закончить работу");
            i = scanner.nextInt();
            switch (i){
                case 1:
                    System.out.println("Вы решили добавить запись");
                    System.out.println("ID: 1 - Огурцы, 2 - Хлеб, 3 - Молоко, 4 - Помидоры, 5 - Шоколад, 6 - Пельмени, 7 - Тушка курицы, 8 - Макароны, 9 - Рис, 10 - Картофель");
                    System.out.println("Введите id продукта");
                    int product_id = scanner.nextInt();
                    System.out.println("Введите цену продукта");
                    float price = scanner.nextFloat();
                    System.out.println("Введите год продажи продукта");
                    int year = scanner.nextInt();
                    ConnectionDb.AddRecord(product_id,price,year);
                    break;
                case 2:
                    System.out.println("Вы решили вывести таблицу");
                    ConnectionDb.ReadDB();
                    break;
                case 3:
                    System.out.println("Вы решили вывести записи по году");
                    System.out.println("Введите год продажи продукта");
                    year = scanner.nextInt();
                    ConnectionDb.ReadRecordByYear(year);
                    break;
                case 4:
                    System.out.println("Вы решили вывести записи по id");
                    System.out.println("ID: 1 - Огурцы, 2 - Хлеб, 3 - Молоко, 4 - Помидоры, 5 - Шоколад, 6 - Пельмени, 7 - Тушка курицы, 8 - Макароны, 9 - Рис, 10 - Картофель");
                    System.out.println("Введите id продукта");
                    product_id = scanner.nextInt();
                    ConnectionDb.ReadRecordById(product_id);
                    break;
                case 5:
                    System.out.println("Вы решили вывести запись по id и году");
                    System.out.println("ID: 1 - Огурцы, 2 - Хлеб, 3 - Молоко, 4 - Помидоры, 5 - Шоколад, 6 - Пельмени, 7 - Тушка курицы, 8 - Макароны, 9 - Рис, 10 - Картофель");
                    System.out.println("Введите id продукта");
                    product_id = scanner.nextInt();
                    System.out.println("Введите год продажи продукта");
                    year = scanner.nextInt();
                    ConnectionDb.ReadRecordByYearAndID(year,product_id);
                    break;
                case 6:
                    System.out.println("Вы решили изменить запись");
                    System.out.println("Введите id продукта");
                    System.out.println("ID: 1 - Огурцы, 2 - Хлеб, 3 - Молоко, 4 - Помидоры, 5 - Шоколад, 6 - Пельмени, 7 - Тушка курицы, 8 - Макароны, 9 - Рис, 10 - Картофель");
                    product_id = scanner.nextInt();
                    System.out.println("Введите год продажи продукта");
                    year = scanner.nextInt();
                    System.out.println("Введите новую цену для продукта");
                    price = scanner.nextFloat();
                    ConnectionDb.UpdateRecordByIdAndYear(product_id,year,price);
                    break;
                case 7:
                    System.out.println("Вы решили удалить запись");
                    System.out.println("Введите id продукта");
                    System.out.println("ID: 1 - Огурцы, 2 - Хлеб, 3 - Молоко, 4 - Помидоры, 5 - Шоколад, 6 - Пельмени, 7 - Тушка курицы, 8 - Макароны, 9 - Рис, 10 - Картофель");
                    product_id = scanner.nextInt();
                    System.out.println("Введите год продажи продукта");
                    year = scanner.nextInt();
                    ConnectionDb.DeleteRecordByIdAndYear(product_id,year);
                    break;
            }
        }
        ConnectionDb.CloseDB();
    }
}
