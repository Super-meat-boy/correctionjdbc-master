package co.simplon.pf1.correctionjdbc;

import java.util.List;

import co.simplon.pf1.correctionjdbc.database.DatabaseConnection;
import co.simplon.pf1.correctionjdbc.model.FoodAttribute;

public class App {


    public static void main(String[] args) {
        // Je veux me connecter à la base
        String url = "jdbc:postgresql://localhost:5432/food";
        String user = "fooduser";
        String password = "fooddb";
        DatabaseConnection dbConnect = new DatabaseConnection(url, user, password);

        // Je veux ranger des aliments dans une liste
        List<FoodAttribute> foodList = FoodAttribute.listFoodAttributesWhereEnergyMore200OrderDesc(dbConnect);

        // Je veux l'afficher
        for (FoodAttribute foodAttribute : foodList) {
            System.out.println(foodAttribute);
        }

        // Je veux trier ma liste par ordre alpha sur le nom
        foodList.sort(null);

        // Je veux l'afficher
        for (FoodAttribute foodAttribute : foodList) {
            System.out.println(foodAttribute);
        }

        // Je m'assure que je ferme la connection à la BDD
        dbConnect.closeConnection();
    }
}
