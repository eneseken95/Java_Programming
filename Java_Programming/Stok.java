import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Stok {
    private static Map<String, Integer> urunStok = new HashMap<>();

    public static void main(String[] args) {

        urunStok.put("Yağ", 50);
        urunStok.put("Peynir", 30);
        urunStok.put("Yoğurt", 100);
        urunStok.put("Süt", 80);
        urunStok.put("Ekmek", 150);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Ürünleri Göster");
            System.out.println("2. Stok Ekle");
            System.out.println("3. Stok Çıkar");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    urunleriGoster();
                    break;
                case 2:
                    stokEkle();
                    break;
                case 3:
                    stokCikar();
                    break;
                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }

    private static void urunleriGoster() {
        System.out.println("Ürünler ve Stok Miktarı:");
        for (Map.Entry<String, Integer> entry : urunStok.entrySet()) {
            String urunAdi = entry.getKey();
            int stokMiktar = entry.getValue();
            System.out.println(urunAdi + " - Stok: " + stokMiktar);
        }
    }

    private static void stokEkle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Stok eklemek istediğiniz ürünü girin: ");
        String urunAdi = scanner.nextLine();

        if (urunStok.containsKey(urunAdi)) {
            System.out.print("Eklemek istediğiniz miktarı girin: ");
            int miktar = scanner.nextInt();
            scanner.nextLine();

            int mevcutStok = urunStok.get(urunAdi);
            urunStok.put(urunAdi, mevcutStok + miktar);
            System.out.println(urunAdi + " stok miktarı güncellendi. Yeni Stok: " + (mevcutStok + miktar));
        } else {
            System.out.println("Ürün bulunamadı!");
        }
    }

    private static void stokCikar() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Stok çıkarmak istediğiniz ürünü girin: ");
        String urunAdi = scanner.nextLine();

        if (urunStok.containsKey(urunAdi)) {
            System.out.print("Çıkarmak istediğiniz miktarı girin: ");
            int miktar = scanner.nextInt();
            scanner.nextLine();

            int mevcutStok = urunStok.get(urunAdi);
            if (miktar <= mevcutStok) {
                urunStok.put(urunAdi, mevcutStok - miktar);
                System.out.println(urunAdi + " stok miktarı güncellendi. Yeni Stok: " + (mevcutStok - miktar));
            } else {
                System.out.println("Yetersiz stok miktarı!");
            }
        } else {
            System.out.println("Ürün bulunamadı!");
        }
    }
}
