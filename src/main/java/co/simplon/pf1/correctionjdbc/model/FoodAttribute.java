package co.simplon.pf1.correctionjdbc.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.simplon.pf1.correctionjdbc.database.DatabaseConnection;

public class FoodAttribute implements Comparable<FoodAttribute> {

    private final int id;
    private final int category;
    private final String name;
    private final int energy;
    private final int prot;
    private final int fat;
    private final int carb;


    public FoodAttribute(int id, int category, String name, int energy, int prot, int fat, int carb) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.energy = energy;
        this.prot = prot;
        this.fat = fat;
        this.carb = carb;
    }

    public int getId() {
        return id;
    }

    public int getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    public int getProt() {
        return prot;
    }

    public int getFat() {
        return fat;
    }

    public int getCarb() {
        return carb;
    }

    @Override
    public String toString() {
        return "FoodAttribute [name=" + name + ", energy=" + energy + "]";
    }

    @Override
    public int compareTo(FoodAttribute o) {
        return this.name.compareToIgnoreCase(o.getName());
    }

    /**
     * Document static method
     *
     * @param dbConnect
     * @return
     */
    public static List<FoodAttribute> listFoodAttributesWhereEnergyMore200OrderDesc(DatabaseConnection dbConnect) {
        // Je veux ranger des aliments dans une liste
        List<FoodAttribute> foodList = new ArrayList<>();
        // Je veux récupérer tous les aliments de ma base de données
        // dont l'energie est > 200 et rangé par energie decroissante en SQL
        String sql = "SELECT id, category_id, name, energy, protein, carb, fat "
                + "FROM food_attribute WHERE energy > ? ORDER BY energy DESC";
        try (PreparedStatement sqlStatement = dbConnect.getConnection().prepareStatement(sql)) {
            sqlStatement.setInt(1, 200);
            ResultSet rs = sqlStatement.executeQuery();

            while (rs.next()) {
                FoodAttribute foodAttribute = new FoodAttribute(rs.getInt("id"), rs.getInt("category_id"), rs.getString("name"),
                        rs.getInt("energy"), rs.getInt("protein"), rs.getInt("fat"), rs.getInt("carb"));
                foodList.add(foodAttribute);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foodList;
    }

}
