package ru.anura.database;

import java.sql.*;
import java.util.ArrayList;

public class ConnectionDb {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;
    public static final String NAME_USER = "root";
    public static final String PASSWORD = "root";
    public static final String URL = "jdbc:mysql://localhost:3306/mysql";

    // --------ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ--------
    public static void Connection() throws ClassNotFoundException, SQLException {
        connection = null;
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:prices");
        System.out.println("Database is connected");
    }

    // --------Создание таблицы--------
    public static void CreateDB() throws ClassNotFoundException, SQLException {
        statement = connection.createStatement();
        statement.execute("CREATE TABLE if not exists products_price_history (id INTEGER PRIMARY KEY, product_id INT, product_name TEXT, price FLOAT, year INT)");

        System.out.println("Database is created or is already existed");
    }

    //--------Добавление записи--------
    public static void AddRecord(int product_id, float price, int year) throws SQLException {
        String sql = "SELECT * FROM products_price_history WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, product_id);
        ResultSet rs = preparedStatement.executeQuery();
        String name = "";
        if (rs.next()) {
            name = rs.getString("product_name");
        } else {
            System.out.println("Такой продукт не найден");
        }
        if (name != null && !name.equals("")) {
            String query = "INSERT INTO products_price_history (product_id, price, year,product_name) VALUES (?, ?, ?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, product_id);
            preparedStatement.setFloat(2, price);
            preparedStatement.setInt(3, year);
            preparedStatement.setString(4, name);
            preparedStatement.executeUpdate();
        }
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException {
        resultSet = statement.executeQuery("SELECT * FROM products_price_history");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int product_id = resultSet.getInt("product_id");
            String product_name = resultSet.getString("product_name");
            float price = resultSet.getFloat("price");
            int year = resultSet.getInt("year");
            System.out.println("ID = " + id);
            System.out.println("product_id = " + product_id);
            System.out.println("product_name = " + product_name);
            System.out.println("price = " + price);
            System.out.println("year = " + year);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // -------- Вывод записи по id--------
    public static void ReadRecordById(int product_id) throws SQLException {
        String query = "SELECT * FROM products_price_history WHERE product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, product_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            //int prod_id = resultSet.getInt("product_id");
            String product_name = resultSet.getString("product_name");
            int price = resultSet.getInt("price");
            int year = resultSet.getInt("year");
            System.out.println("ID = " + id);
            System.out.println("product_id = " + product_id);
            System.out.println("product_name = " + product_name);
            System.out.println("price = " + price);
            System.out.println("year = " + year);
            System.out.println();

        }
        System.out.println("Записи выведены");
    }

    // -------- Вывод записи по году--------
    public static void ReadRecordByYear(int year) throws SQLException {
        String query = "SELECT * FROM products_price_history WHERE year = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int prod_id = resultSet.getInt("product_id");
            String product_name = resultSet.getString("product_name");
            int price = resultSet.getInt("price");
            //int year = resultSet.getInt("year");
            System.out.println("ID = " + id);
            System.out.println("product_id = " + prod_id);
            System.out.println("product_name = " + product_name);
            System.out.println("price = " + price);
            System.out.println("year = " + year);
            System.out.println();

        }
        System.out.println("Записи выведены");
    }

    // -------- Вывод записи по году и ID--------
    public static void ReadRecordByYearAndID(int year, int product_id) throws SQLException {
        String query = "SELECT * FROM products_price_history WHERE year = ? AND product_id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, year);
        preparedStatement.setInt(2, product_id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            //int prod_id = resultSet.getInt("product_id");
            String product_name = resultSet.getString("product_name");
            int price = resultSet.getInt("price");
            //int year = resultSet.getInt("year");
            System.out.println("ID = " + id);
            System.out.println("product_id = " + product_id);
            System.out.println("product_name = " + product_name);
            System.out.println("price = " + price);
            System.out.println("year = " + year);
            System.out.println();

        }
        System.out.println("Записи выведены");
    }

    // -------- Обновление записи по id и году--------
    public static void UpdateRecordByIdAndYear(int product_id, int year, float price) throws SQLException {
        String query = "UPDATE products_price_history SET price = ? WHERE product_id = ? AND year = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setFloat(1, price);
        preparedStatement.setInt(2, product_id);
        preparedStatement.setInt(3, year);
        preparedStatement.executeUpdate();
        System.out.println("Запись обновлена");
    }

    //--------Удаление записи по Id и году--------
    public static void DeleteRecordByIdAndYear(int product_id, int year) throws SQLException {
        String query = "DELETE FROM products_price_history WHERE product_id = ? AND year = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, product_id);
        preparedStatement.setInt(2, year);
        preparedStatement.executeUpdate();
        System.out.println("Запись удалена");
    }

    //--------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        connection.close();
        statement.close();
        resultSet.close();

        System.out.println("Соединения закрыты");
    }
    /*// --------Заполнение таблицы--------
    public static void WriteDB(String data) throws SQLException {
        statement.execute(data);
        //statement.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Vasya', 321789); ");
        //statement.execute("INSERT INTO 'users' ('name', 'phone') VALUES ('Masha', 456123); ");

        //System.out.println("Таблица заполнена");
    }

    public static void isCanBuyChanged(int previousCanBuy) throws SQLException, ClassNotFoundException {
        resultSet = statement.executeQuery("SELECT * FROM pets");
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pet_id = resultSet.getString("pet_id");
            int canBuy = resultSet.getInt("canBuy");
            float price = resultSet.getFloat("price");

            // проверка изменений данных
            if (canBuy != previousCanBuy) {
                // выполнить необходимые действия
                System.out.println("CanBuy был изменен");
                // сохранение предыдущих значений
                previousCanBuy = canBuy;
            } else {
                System.out.println("CanBuy не менялся");
            }


        }
    }
    // --------Получение всех Pet_Id--------
    public static ArrayList<String> getPet_id() throws ClassNotFoundException, SQLException {
        resultSet = statement.executeQuery("SELECT * FROM pets");
        ArrayList <Integer> canBuys = new ArrayList<>();
        while (resultSet.next()) {
            int canBuy = resultSet.getInt("canBuy");
            canBuys.add(canBuy);
        }
        return canBuys;
    }
    // --------Добавление новых петов--------
    public static void addNewPet_id(String data) throws ClassNotFoundException, SQLException {
        statement.execute(data);
    }

    // --------Получение прошлого CanBuy--------
    public static ArrayList<Integer> getPreviousCanBuy() throws ClassNotFoundException, SQLException {
        resultSet = statement.executeQuery("SELECT * FROM pets");
        ArrayList <Integer> canBuys = new ArrayList<>();
        while (resultSet.next()) {
            int canBuy = resultSet.getInt("canBuy");
            canBuys.add(canBuy);
        }
        return canBuys;
    }

    // -------- Вывод таблицы--------
    public static void ReadDB() throws ClassNotFoundException, SQLException {
        resultSet = statement.executeQuery("SELECT * FROM pets");

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String pet_id = resultSet.getString("pet_id");
            int canBuy = resultSet.getInt("canBuy");
            float price = resultSet.getFloat("price");
            System.out.println("ID = " + id);
            System.out.println("pet_id = " + pet_id);
            System.out.println("canBuy = " + canBuy);
            System.out.println("price = " + price);
            System.out.println();
        }

        System.out.println("Таблица выведена");
    }

    // --------Закрытие--------
    public static void CloseDB() throws ClassNotFoundException, SQLException {
        connection.close();
        statement.close();
        resultSet.close();

        System.out.println("Соединения закрыты");
    }
    // --------Очистка--------
    public static void DeleteDB() throws ClassNotFoundException, SQLException {
        statement.executeUpdate("DELETE FROM pets");
    }*/

}

