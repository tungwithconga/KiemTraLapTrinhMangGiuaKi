package com.mycompany.kiemtragiuaki.laptrinhmang;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class KiemTraGiuaKiLapTrinhMang {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress diaChiServer = InetAddress.getByName("localhost");
            byte[] guiDuLieu;
            byte[] nhanDuLieu = new byte[1024];

            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Nhap mot so nguyen duong (n): ");
                String n = scanner.nextLine();

                guiDuLieu = n.getBytes();
                DatagramPacket goiGui = new DatagramPacket(guiDuLieu, guiDuLieu.length, diaChiServer, 9876);
                clientSocket.send(goiGui);

                DatagramPacket goiNhan = new DatagramPacket(nhanDuLieu, nhanDuLieu.length);
                clientSocket.receive(goiNhan);
                String ketQua = new String(goiNhan.getData(), 0, goiNhan.getLength());

                System.out.println(" Server: " + ketQua);

                if (ketQua.contains("Khong co so nguyen to") || !ketQua.contains("Nhap khong hop le")) {
                    break;
                }
            }
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
