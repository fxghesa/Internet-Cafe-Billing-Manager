package ISP;
import com.mysql.jdbc.Driver;
import java.sql.*;
/*
 - Purwanto         2210141039
 - Florensius Ghesa 2210141055
 2 D4 TK B
*/
public class Admin extends Utama {
    private String kodeAdmin="1",Password="2";
    Statement st;
    ResultSet rs;
    Connection adminKonek;
    public boolean loginAdmin(String kodeAdmin,String Password){
        bacaAdmin();
        if((kodeAdmin.equals(this.kodeAdmin))&&(Password.equals(this.Password))){
            return true;
        } else
        return false;
    }
    public boolean ubahAkun(String kodeAdmin,String Password,String Password2){
        if(Password.equals(Password2)){
            updateID();
            this.kodeAdmin=kodeAdmin;
            this.Password=Password;
            return true;
        }else
            return false;
    }
    protected void getKonekAdmin() throws SQLException{
        try{
            String id, pass, driver, url;
            id = "root";
            pass = "";
            driver = "com.mysql.jdbc.Driver";
            url = "jdbc:mysql://localhost:3306/coba";
            Class.forName(driver).newInstance();
            adminKonek = DriverManager.getConnection(url,id,pass);
            if (adminKonek==null){
                System.out.println("koneksi gagal");
            }else{
                System.err.println("koneksi berhasil");
            }
        }catch (Exception e){
            System.err.println(""+e.getMessage());
        }
    }
    private void bacaAdmin(){
        String sql = "select * from admin";
        try {
            getKonekAdmin();
            st = adminKonek.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()){
                String kolom1 = rs.getString(1);
                String kolom2 = rs.getString(2);
                String kolom[] = {kolom1, kolom2};
                this.kodeAdmin=kolom1;
                this.Password=kolom2;
            }
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
        
    public void updateID(){
        int hasil=0;
        String id = ""+this.kodeAdmin;
        String password = ""+this.Password;
        String sql = "update admin set id='"+id+"' where password='"+password+"'";
        try {
            Statement st = adminKonek.createStatement();
            hasil = st.executeUpdate(sql);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        //System.out.println((hasil==1)?"update berhasil":"update gagal");
    }
}
