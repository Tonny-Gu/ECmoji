package proj.ecmoji.data;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import proj.ecmoji.util.TXTtokenLoader;

public class StorageServer {
    final private static String dbPath = "data/database.json";
    final public static Set<ScoredProduct> products = new CopyOnWriteArraySet<>();
    final private static Gson gson = new Gson();

    public static void load() throws Exception {
        Set<ScoredProduct> list = gson.fromJson(TXTtokenLoader.readLine(dbPath), new TypeToken<Set<ScoredProduct>>(){}.getType());
        if(list!=null) products.addAll(list);
    }
    public static void save() throws Exception {
        try(PrintWriter writer = new PrintWriter(new File(dbPath))) {
            writer.write(gson.toJson(products));
            writer.flush();
        } catch (Exception e) {throw(e);}
    }
    public static Set<ScoredProduct> getProductList() {return products;}
    public static void addProducts(Set<ScoredProduct> products) {
        StorageServer.products.addAll(products);
    }
    public static void addProducts(List<ScoredProduct> products) {
        StorageServer.products.addAll(products);
    }
    public static void addProduct(ScoredProduct product) {
        StorageServer.products.add(product);
    }
}