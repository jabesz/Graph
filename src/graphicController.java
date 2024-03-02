import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class graphicController implements Initializable{

    @FXML
    private Button BtnAddPdf;

    @FXML
    private Button btnAdd;

    @FXML
    private PieChart chartPie;

    @FXML
    private TextArea txtChartDataDisplay;

    @FXML
    private TextField txtProdName;

    @FXML
    private TextField txtSales;

    JdbcDao jdbc;
    
    ObservableList<PieChart.Data> chartData = null;

    Document document = new Document();

    @FXML
    void onAdd(ActionEvent event) {
        
        Connection conn = jdbc.getConnection();

        try {

            String name = txtProdName.getText();
            String sale = txtSales.getText();

            if (name.isEmpty() || sale.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Enter the text fields");

            } else {
                PreparedStatement ps = conn.prepareStatement("INSERT INTO graphic (name, sale) VALUES (?,?)");
                ps.setString(1, name);
                ps.setString(2, sale);

                int res = ps.executeUpdate();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Success");

                }

            }

            String chartItem = txtProdName.getText();
            double value = new Integer(txtSales.getText()).doubleValue();

            PieChart.Data data = new PieChart.Data(chartItem, value);
            chartData.add(data);
            chartPie.setData(chartData);

            txtChartDataDisplay.appendText("[" + chartItem + ", " + txtSales.getText() + "] \n");

        } catch (Exception e) {
            
        }
    }

    @FXML
    void addPdf(ActionEvent event) {
        generatePdf();
    }

    private void generatePdf() {
        Connection conn = jdbc.getConnection();

        Document doc = new Document();

        try {
            PdfWriter.getInstance(doc, new FileOutputStream("Graph.pdf"));
            doc.open();
            doc.add(new Paragraph("Graph"));

        } catch (Exception e) {
            System.out.println("" + e.getMessage());

        } finally {
            doc.close();

        }

        try {
            Desktop.getDesktop().open(new File("Graph.pdf"));

        } catch (Exception ex) {
            System.out.println("" + ex.getMessage());
        }

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        jdbc = new JdbcDao();
        chartData = FXCollections.observableArrayList();
        chartPie.setTitle("Products sales in year");
    }
    
}
