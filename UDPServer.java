package com.mycompany.kiemtragiuaki.laptrinhmang;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            byte[] nhanDuLieu = new byte[1024];
            byte[] guiDuLieu;

            while (true) {
                DatagramPacket goiNhan = new DatagramPacket(nhanDuLieu, nhanDuLieu.length);
                serverSocket.receive(goiNhan);
                String nStr = new String(goiNhan.getData(), 0, goiNhan.getLength());
                InetAddress diaChiClient = goiNhan.getAddress();
                int congClient = goiNhan.getPort();

                int n;
                try {
                    n = Integer.parseInt(nStr.trim());
                    if (n <= 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    String thongBaoLoi = "Nhap khong hop le! Vui long nhap so nguyen duong.";
                    guiDuLieu = thongBaoLoi.getBytes();
                    DatagramPacket goiGui = new DatagramPacket(guiDuLieu, guiDuLieu.length, diaChiClient, congClient);
                    serverSocket.send(goiGui);
                    continue;
                }

                StringBuilder ketQua = new StringBuilder();
                for (int i = 2; i < n; i++) {
                    if (laSoNguyenTo(i) && i % 5 == 0) {
                        ketQua.append(i).append(" ");
                    }
                }
                if (ketQua.length() == 0) {
                    ketQua.append("Khong co so nguyen to nao chia het cho 5.");
                }

                guiDuLieu = ketQua.toString().getBytes();
                DatagramPacket goiGui = new DatagramPacket(guiDuLieu, guiDuLieu.length, diaChiClient, congClient);
                serverSocket.send(goiGui);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean laSoNguyenTo(int num) {
        if (num < 2) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
