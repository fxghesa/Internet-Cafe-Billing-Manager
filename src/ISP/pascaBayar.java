package ISP;
import com.mysql.jdbc.Driver;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/*
 - Purwanto         2210141039
 - Florensius Ghesa 2210141055
 2 D4 TK B
*/
public class pascaBayar extends Utama{
    private int pilihPaket,jumUserPasca=1,history=0,jumUserPasca2=0;
    private String Email="1",Password="2",Nama,NoTelp,tanggal;
    private Statement st;
    private ResultSet rs;
    private Connection pascaKonek;
    public pascaBayar(){
        //todo
    }
    public void setpascaBayar(String nama,String notelp,String email,String password){
        this.Nama = nama;
        this.NoTelp = notelp;
        this.Email = email;
        this.Password = password;
        sigmaUser();
    }
    public boolean loginPasca(String email,String password){
        String sql = "select * from pascabayar";
        try {
            getKonekPasca();
            st = pascaKonek.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String kolom1 = rs.getString(1);
                String kolom2 = rs.getString(2);
                String kolom3 = rs.getString(3);
                String kolom4 = rs.getString(4);
                if(kolom1.equals(email) && kolom2.equals(password)){
                    this.Email=kolom1;
                    this.Password=kolom2;
                    this.Nama=kolom3;
                    this.NoTelp=kolom4;
                    return true;
                }
                String kolom[] = {kolom1, kolom2, kolom3,kolom4};
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return false;
    }
    public void pilihPaket(int pilihPaket){
        this.pilihPaket = pilihPaket;
    }
    public String getNama(){
        return this.Nama;
    }
    public String getNoTelp(){
        return this.NoTelp;
    }
    public String getEmail(){
        return this.Email;
    }
    
    public void sigmaUser(){
        String sql = "select * from pascabayar";
        try {
            getKonekPasca();
            st = pascaKonek.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                ++this.jumUserPasca;
                String kolom1 = rs.getString(1);
                String kolom2 = rs.getString(2);
                String kolom3 = rs.getString(3);
                String kolom4 = rs.getString(4);
                String kolom[] = {kolom1, kolom2, kolom3,kolom4};
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    protected void getKonekPasca() throws SQLException{
        try{
            String id, pass, driver, url;
            id = "root";
            pass = "";
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/coba";
            Class.forName(driver).newInstance();
            pascaKonek = DriverManager.getConnection(url,id,pass);
            if (pascaKonek==null){
                System.out.println("koneksi gagal");
            }else{
                System.out.println("koneksi berhasil");
            }
        }catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }
    
    public void masukDataBase() throws SQLException{
        this.jumUserPasca2 = jumUserPasca / 2;
        System.out.println("now : "+this.jumUserPasca2);
        String no = ""+this.jumUserPasca2;
        String nama = ""+this.Nama;
        String notelp = ""+this.NoTelp;
        String email = ""+this.Email;
        String password = ""+this.Password;
        getKonekPasca();
        try{
            st = pascaKonek.createStatement();
            st.executeUpdate("insert into pascabayar "
                + "values('"+email+"','"+password+"','"+nama+"','"+notelp+"');");
        }catch(Exception e){
            System.err.println("Error tulis : "+e.getMessage());
        }
    }
    private String getTanggal() { 
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); 
        java.util.Date date = new java.util.Date(); 
        return dateFormat.format(date); 
    }
    public void masukHistory() throws SQLException{
        getKonekPasca();
        ++history;
        this.tanggal=pasca.getTanggal();
        String no = ""+this.history;
        String email = this.Email;
        String paket = ""+this.pilihPaket;
        String tanggal = ""+this.tanggal;
        try{
            st = pascaKonek.createStatement();
            st.executeUpdate("insert into history "
                + "values('"+no+"','"+email+"','"+paket+"','"+tanggal+"');");
        }catch(Exception e){
            System.err.println("Error tulis : "+e.getMessage());
        }
    }
}
