public class Grafico {
    private int id;
    private String name;
    private String sale;

    public Grafico(int id, String name, String sale) {
        this.id = id;
        this.name = name;
        this.sale = sale;
    }
    
    public Grafico(String string, String string2, String string3) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

}
