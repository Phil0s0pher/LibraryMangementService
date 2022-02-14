package com.epam.libraryservice.client;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

        public static void main(String [] args) throw SQLException{

            try{
//                Class.for("com.mysql.jdbc");
                Connection conn= DriverManager.getConnection("url", "userName","password");
                Statement st= conn.createStatement();
                ResultSet res= st.executeQuery("select * from demo");
//                if(res.isEmpty()) throw SQLException();
                while(res.next()){
                    System.out.println(res.getString(0)+ "" +res.getString(1));
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }

        }


}
