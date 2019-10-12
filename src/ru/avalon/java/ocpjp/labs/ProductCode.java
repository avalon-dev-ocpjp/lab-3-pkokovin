package ru.avalon.java.ocpjp.labs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

/**
 * Класс описывает представление о коде товара и отражает соответствующую 
 * таблицу базы данных Sample (таблица PRODUCT_CODE).
 * 
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class ProductCode {
    /**
     * Код товара
     */
    private String code;
    /**
     * Кода скидки
     */
    private char discountCode;
    /**
     * Описание
     */
    private String description;
    /**
     * Основной конструктор типа {@link ProductCode}
     * 
     * @param code код товара
     * @param discountCode код скидки
     * @param description описание 
     */
    public ProductCode(String code, char discountCode, String description) {
        this.code = code;
        this.discountCode = discountCode;
        this.description = description;
    }
    /**
     * Инициализирует объект значениями из переданного {@link ResultSet}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, 
     * содержащего все поля таблицы PRODUCT_CODE базы данных Sample.
     */
    private ProductCode(ResultSet set) throws SQLException {
        /*
         * Реализован конструктор класса ProductCode
         */
        code = set.getString("PROD_CODE");
        discountCode = set.getString("DISCOUNT_CODE").toCharArray()[0];
        description = set.getString("DESCRIPTION");        
    }
    /**
     * Возвращает код товара
     * 
     * @return Объект типа {@link String}
     */
    public String getCode() {
        return code;
    }
    /**
     * Устанавливает код товара
     * 
     * @param code код товара
     */
    public void setCode(String code) {
        this.code = code;
    }
    
    /**
     * Возвращает код скидки
     * 
     * @return Объект типа {@link String}
     */
    public char getDiscountCode() {
        return discountCode;
    }
    /**
     * Устанавливает код скидки
     * 
     * @param discountCode код скидки
     */
    public void setDiscountCode(char discountCode) {
        this.discountCode = discountCode;
    }
    /**
     * Возвращает описание
     * 
     * @return Объект типа {@link String}
     */
    public String getDescription() {
        return description;
    }
    /**
     * Устанавливает описание
     * 
     * @param description описание
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Хеш-функция типа {@link ProductCode}.
     * 
     * @return Значение хеш-кода объекта типа {@link ProductCode}
     */
    @Override
    public int hashCode() {
        /*
         * TODO #06 Реализуйте метод hashCode
         */
        return Objects.hash(code, discountCode, description);
    }
    /**
     * Сравнивает некоторый произвольный объект с текущим объектом типа 
     * {@link ProductCode}
     * 
     * @param obj Объект, скоторым сравнивается текущий объект.
     * @return true, если объект obj тождественен текущему объекту. В обратном 
     * случае - false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof ProductCode)) return false;
        ProductCode other = (ProductCode) obj;
        return this != null && code.equals(other.code) && discountCode == other.discountCode && description.equalsIgnoreCase(other.description);
    }
    /**
     * Возвращает строковое представление кода товара.
     * 
     * @return Объект типа {@link String}
     */
    @Override
    public String toString() {
       /*
         * Реализован метод toString
         */
        String result ="CODE = " + code + " " + "DISCOUNT_CODE = " + discountCode + " " + "DESCRIPTION = " + description; 
        return result;
    }
    /**
     * Возвращает запрос на выбор всех записей из таблицы PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getSelectQuery(Connection connection) throws SQLException {
         /*
         * Реализован метод getSelectQuery
         */
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT_CODE");
        return statement;
    }
    /**
     * Возвращает запрос на добавление записи в таблицу PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getInsertQuery(Connection connection) throws SQLException {
        /*
         * Реализован метод getInsertQuery
         */
        String insSql = "INSERT INTO PRODUCT_CODE"
                + "(PROD_CODE, DISCOUNT_CODE, DESCRIPTION) VALUES"
                + "(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(insSql);
        return statement;
    }
    /**
     * Возвращает запрос на обновление значений записи в таблице PRODUCT_CODE 
     * базы данных Sample
     * 
     * @param connection действительное соединение с базой данных
     * @return Запрос в виде объекта класса {@link PreparedStatement}
     */
    public static PreparedStatement getUpdateQuery(Connection connection) throws SQLException {
         /*
         * Реализован метод getUpdateQuery
         */
        String updSql = "UPDATE PRODUCT_CODE SET DISCOUNT_CODE = ?, DESCRIPTION = ? where PROD_CODE = ?";
        PreparedStatement statement = connection.prepareStatement(updSql);
        return statement;
    }
    /**
     * Преобразует {@link ResultSet} в коллекцию объектов типа {@link ProductCode}
     * 
     * @param set {@link ResultSet}, полученный в результате запроса, содержащего 
     * все поля таблицы PRODUCT_CODE базы данных Sample
     * @return Коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> convert(ResultSet set) throws SQLException {
        /*
         * Реализован метод convert
         */
        Collection<ProductCode> temp = new HashSet<>();
        
        while (set.next()) {
            temp.add(new ProductCode(set));
        }
        
        return temp;
    }
    /**
     * Сохраняет текущий объект в базе данных. 
     * <p>
     * Если запись ещё не существует, то выполняется запрос типа INSERT.
     * <p>
     * Если запись уже существует в базе данных, то выполняется запрос типа UPDATE.
     * 
     * @param connection действительное соединение с базой данных
     */
    public void save(Connection connection) throws SQLException {
        /*
         * Реализован метод save
         */
            if (!this.isIdExist(connection)){
            try (PreparedStatement statement = getInsertQuery(connection)){
            statement.setString(1, code);
            statement.setString(2, String.valueOf(discountCode));
            statement.setString(3, description);
            statement.executeUpdate();
        } 
        } else {
            try (PreparedStatement statement = getUpdateQuery(connection)) {
            statement.setString(1, String.valueOf(discountCode));
            statement.setString(2, description);
            statement.setString(3, code);
            statement.executeUpdate();
            } 
        }
    }
    /**
     * Возвращает все записи таблицы PRODUCT_CODE в виде коллекции объектов
     * типа {@link ProductCode}
     * 
     * @param connection действительное соединение с базой данных
     * @return коллекция объектов типа {@link ProductCode}
     * @throws SQLException 
     */
    public static Collection<ProductCode> all(Connection connection) throws SQLException {
        try (PreparedStatement statement = getSelectQuery(connection)) {
            try (ResultSet result = statement.executeQuery()) {
                return convert(result);
            }
        }
    }
    public boolean isIdExist(Connection connection) throws SQLException{
        boolean isExist = false;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCT_CODE WHERE PROD_CODE = ?")) {
        statement.setString(1, code);
        try (ResultSet result = statement.executeQuery()) {
            try {
                String tmp = null;
                
                while (result.next()){
                tmp = result.getString("PROD_CODE");
                }
                if (tmp.equals(code)) isExist = true;
            } catch (NullPointerException e) {
                isExist = false;
            }
        }
        }
        return isExist;
    }
}
