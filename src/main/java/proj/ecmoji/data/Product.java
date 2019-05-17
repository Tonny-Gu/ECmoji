package proj.ecmoji.data;

public class Product{
    private final String name, url;
    public Product(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {return name;}
    public String getURL() {return url;}

    @Override
    public int hashCode() {
        return name.hashCode() + url.hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        return this.getName().equals(((Product)obj).getName())
            && this.getURL().equals(((Product)obj).getURL());
    }
}