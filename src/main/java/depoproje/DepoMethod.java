package depoproje;

import java.util.HashMap;
import java.util.Scanner;

public class DepoMethod extends Depo{
    int id = getId();
    private HashMap<Integer, Depo> urunListesi = new HashMap<>();
    Scanner input = new Scanner(System.in);

    public DepoMethod(String urunAdi, String uretici, String birim, int miktar, String raf) {
        super(urunAdi, uretici, birim, miktar, raf);
    }

    public void giris() {
        System.out.print("\nDepo Uygulama Programı\n" +
                "----------------------\n" +
                "1) Ürün Tanımla\n" +
                "2) Ürün Listele\n" +
                "3) Ürün Girişi\n" +
                "4) Ürünü Rafa Koy\n" +
                "5) Ürün Çıkışı\n" +
                "6) Çıkış\n" +
                "Yapmak istediğiniz işlemi tuşlayınız: ");
        int secim = input.nextInt();
        switch (secim) {
            case 1:
                urunTanimla();
                giris();
                break;
            case 2:
                urunListele();
                giris();
                break;
            case 3:
                urunGirisi();
                giris();
                break;
            case 4:
                urunRafakoy();
                giris();
                break;
            case 5:
                urunCikisi();
                giris();
                break;
            case 6:
                break;
            default:
                System.out.println("Hatalı Giriş. Tekrar Deneyiniz");
                giris();
                break;
        }
    }

    public void urunTanimla() {
        id++;
        System.out.print("Ürünün Adını giriniz: ");
        String urunAdi = (input.next());
        System.out.print("Üreticiyi giriniz: ");
        String uretici = (input.next());
        System.out.print("Birimi giriniz: ");
        String birimi = (input.next());
        Depo urunTanimla = new Depo(urunAdi, uretici, birimi);
        urunListesi.put(id, urunTanimla);
    }

    public void urunListele() {
        System.out.printf("\n%7s %12s %12s %12s %12s %12s\n", "id", "Ürün Adı", "Üretici", "Miktar", "Birim", "Raf");
        System.out.println("------------------------------------------------------------------------------");
        for (int i = 1; i <= urunListesi.size(); i++) {
            Depo liste = urunListesi.get(i);
            System.out.printf("%7d %12s %12s %12d %12s %12s\n", i, liste.getUrunAdi(), liste.getUretici(), liste.getMiktar(), liste.getBirim(), liste.getRaf());
        }
    }

    public void urunGirisi() {
        urunListele();
        System.out.print("Ürün Girişi ===>  Id :");
        int idx = input.nextInt();
        Depo liste = urunListesi.get(idx);
        System.out.print("Miktarını girin : ");
        int miktar = Math.abs(input.nextInt());
        String raf = liste.getRaf();
        if (liste.getRaf().equals("-")) {
            System.out.print("Rafı girin : ");
            raf = input.next();
        }
        Depo urunGiris = new Depo(liste.getUrunAdi(), liste.getUretici(), liste.getBirim(), (liste.getMiktar() + miktar), raf);
        urunListesi.put(idx, urunGiris);
        urunListele();
    }

    public void urunRafakoy() {
        urunListele();
        System.out.print("Ürünü Rafa Koy ===>  Id :");
        Depo liste = urunListesi.get(input.nextInt());
        System.out.print("Rafı girin : ");
        String raf = input.next();
        liste.setRaf(raf);
        urunListele();
    }

    public void urunCikisi() {
        urunListele();
        System.out.print("Ürün Çıkışı ===>  Id :");
        Depo liste = urunListesi.get(input.nextInt());
        System.out.print("Miktarı girin : ");
        int miktar = Math.abs(input.nextInt());
        liste.setMiktar(miktar);
        System.out.println("(Kalan " + liste.getMiktar() + " " + liste.getBirim() + " " + liste.getUrunAdi() + ")");
        urunListele();
    }
}
