import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Market {
    private static Map<String, Double> urunFiyatlari = new HashMap<>();
    private static Map<String, Integer> sepet = new HashMap<>();
    private static List<String> eklenenUrunler = new ArrayList<>();

    public static void main(String[] args) {
        urunFiyatlari.put("Elma", 2.0);
        urunFiyatlari.put("Armut", 1.5);
        urunFiyatlari.put("Muz", 3.0);
        urunFiyatlari.put("Çilek", 4.0);
        urunFiyatlari.put("Portakal", 2.5);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Ürünleri Göster");
            System.out.println("2. Ürün Ekle");
            System.out.println("3. Sepeti Göster");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    urunleriGoster();
                    break;
                case 2:
                    urunEkle();
                    break;
                case 3:
                    sepetiGoster();
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
        System.out.println("Mevcut Ürünler:");
        for (Map.Entry<String, Double> entry : urunFiyatlari.entrySet()) {
            String urunAdi = entry.getKey();
            double fiyat = entry.getValue();
            System.out.println(urunAdi + " - Fiyat: " + fiyat + " TL");
        }
    }

    private static void urunEkle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Eklemek istediğiniz ürünü girin: ");
        String urunAdi = scanner.nextLine();

        if (urunFiyatlari.containsKey(urunAdi)) {
            System.out.print("Miktarı girin: ");
            int miktar = scanner.nextInt();
            scanner.nextLine();

            if (eklenenUrunler.contains(urunAdi)) {
                System.out.println(urunAdi + " zaten eklenmiş.");
            } else {
                eklenenUrunler.add(urunAdi);
                System.out.println(urunAdi + " eklendi.");
            }

            if (sepet.containsKey(urunAdi)) {
                int eskiMiktar = sepet.get(urunAdi);
                sepet.put(urunAdi, eskiMiktar + miktar);
            } else {
                sepet.put(urunAdi, miktar);
            }
        } else {
            System.out.println("Geçersiz ürün!");
        }
    }

    private static void sepetiGoster() {
        double toplamTutar = 0.0;

        System.out.println("Sepetiniz:");
        for (Map.Entry<String, Integer> entry : sepet.entrySet()) {
            String urunAdi = entry.getKey();
            int miktar = entry.getValue();
            double fiyat = urunFiyatlari.get(urunAdi);
            double tutar = fiyat * miktar;
            toplamTutar += tutar;

            System.out.println(urunAdi + " x" + miktar + " = " + tutar + " TL");
        }

        System.out.println("Toplam Tutar: " + toplamTutar + " TL");
        System.out.println("Eklenen Ürünler: " + eklenenUrunler);
    }
}
