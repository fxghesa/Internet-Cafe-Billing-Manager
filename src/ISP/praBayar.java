package ISP;
import java.io.*;
import java.sql.*;
import com.mysql.jdbc.Driver;
/*
 - Purwanto         2210141039
 - Florensius Ghesa 2210141055
 2 D4 TK B
*/
public class praBayar extends Utama {
    private int rand=43,kali=312,kode=1688;
    private int jumUserPra=1,kodeUser,pilihPaket=0,jumUserPra2=0;
    
    private Connection praKonek;
    private Statement st;
    private ResultSet rs;
    //Connection praKonek;
    public int setpraBayar(){
        sigmaUser();
        kali *= jumUserPra;
        kode = rand * kali;
        return kode;
    }
    public void setKodeUser(){
        this.kodeUser=this.kode;
    }
    public void pilihPaket(int pilihPaket){
        this.pilihPaket = pilihPaket;
    }
    public int getKodeUser(){
        return kodeUser;
    }
    public int getPilihPaket(){
        return pilihPaket;
    }
    protected void getKonekPra() throws SQLException{
        try{
            String id, pass, driver, url;
            id = "root";
            pass = "";
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/coba";
            Class.forName(driver).newInstance();
            praKonek = DriverManager.getConnection(url,id,pass);
            if (praKonek==null){
                System.out.println("koneksi gagal");
            }else{
                System.out.println("koneksi berhasil");
            }
        }catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }
    private void sigmaUser(){
        Object header[]={"Kode","Paket"};
        String sql = "select * from prabayar";
        try {
            getKonekPra();
            st = praKonek.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                ++this.jumUserPra;
                String kolom1 = rs.getString(1);
                String kolom2 = rs.getString(2);
                String kolom[] = {kolom1, kolom2};
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
    public void masukDataBase() throws SQLException{
        jumUserPra2=jumUserPra / 2;
        String no = ""+this.jumUserPra2;
        String kode = ""+this.kodeUser;
        String paket = ""+this.pilihPaket;
        try{
            getKonekPra();
            st = praKonek.createStatement();
            st.executeUpdate("insert into prabayar "
                + "VALUES('"+kode+"','"+paket+"');");
        }catch(Exception e){
            System.err.println("Error tulis : "+e.getMessage());
        }
    }
}
