package model;

public class City {
    private int city_id;
    private String city_name;

    /**
     * Геттеры
     */

    public int getCity_id() {
        return city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    /**
     * Сеттеры
     */

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    /**
     * Конструктор
     */

    public City(int city_id, String city_name) {
        this.city_id = city_id;
        this.city_name = city_name;
    }

    /**
     * Методы
     */
    public String toString() {
        return getCity_name();
    }
}
