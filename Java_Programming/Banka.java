import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Banka {
    private static Map<String, Double> hesaplar = new HashMap<>();
    private static Map<String, String> kullaniciBilgileri = new HashMap<>();
    private static Map<String, Double> yatirilanParalar = new HashMap<>();
    private static Map<String, Double> cekilenParalar = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Kayıt Ol");
            System.out.println("2. Giriş Yap");
            System.out.println("3. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    kayitOl();
                    break;
                case 2:
                    girisYap();
                    break;
                case 3:
                    System.out.println("Çıkış yapılıyor...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }

    private static void kayitOl() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı girin: ");
        String kullaniciAdi = scanner.nextLine();
        System.out.print("Şifrenizi girin: ");
        String sifre = scanner.nextLine();

        if (kullaniciBilgileri.containsKey(kullaniciAdi)) {
            System.out.println("Bu kullanıcı adı zaten kullanılıyor.");
        } else {
            kullaniciBilgileri.put(kullaniciAdi, sifre);
            hesaplar.put(kullaniciAdi, 0.0);
            System.out.println("Kayıt başarılı.");
        }
    }

    private static void girisYap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Kullanıcı adınızı girin: ");
        String kullaniciAdi = scanner.nextLine();
        System.out.print("Şifrenizi girin: ");
        String sifre = scanner.nextLine();

        if (kullaniciBilgileri.containsKey(kullaniciAdi) && kullaniciBilgileri.get(kullaniciAdi).equals(sifre)) {
            System.out.println("Giriş başarılı.");
            hesapIslemleri(kullaniciAdi);
        } else {
            System.out.println("Kullanıcı adı veya şifre hatalı!");
        }
    }

    private static void hesapIslemleri(String kullaniciAdi) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Hesap Bakiyesi Sorgula");
            System.out.println("2. Para Yatır");
            System.out.println("3. Para Çek");
            System.out.println("4. Çıkış");
            System.out.print("Seçiminizi yapın: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    hesapBakiyesiSorgula(kullaniciAdi);
                    break;
                case 2:
                    paraYatir(kullaniciAdi);
                    break;
                case 3:
                    paraCek(kullaniciAdi);
                    break;
                case 4:
                    System.out.println("Çıkış yapılıyor...");
                    System.exit(0);
                default:
                    System.out.println("Geçersiz seçenek!");
            }
        }
    }

    private static void hesapBakiyesiSorgula(String kullaniciAdi) {
        if (hesaplar.containsKey(kullaniciAdi)) {
            double bakiye = hesaplar.get(kullaniciAdi);
            System.out.println("Hesap Bakiyesi: " + bakiye + " TL");
            if (yatirilanParalar.containsKey(kullaniciAdi)) {
                System.out.println("Toplam Yatırılan Para: " + yatirilanParalar.get(kullaniciAdi) + " TL");
            }
            if (cekilenParalar.containsKey(kullaniciAdi)) {
                System.out.println("Toplam Çekilen Para: " + cekilenParalar.get(kullaniciAdi) + " TL");
            }
        } else {
            System.out.println("Hesap bakiyesi bulunamadı.");
        }
    }

    private static void paraYatir(String kullaniciAdi) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Yatırmak istediğiniz miktarı girin: ");
        double miktar = scanner.nextDouble();

        if (hesaplar.containsKey(kullaniciAdi)) {
            double eskiBakiye = hesaplar.get(kullaniciAdi);
            hesaplar.put(kullaniciAdi, eskiBakiye + miktar);
            if (yatirilanParalar.containsKey(kullaniciAdi)) {
                double eskiYatirilan = yatirilanParalar.get(kullaniciAdi);
                yatirilanParalar.put(kullaniciAdi, eskiYatirilan + miktar);
            } else {
                yatirilanParalar.put(kullaniciAdi, miktar);
            }
            System.out.println(miktar + " TL yatırıldı. Yeni bakiye: " + (eskiBakiye + miktar) + " TL");
        } else {
            System.out.println("Hesap bakiyesi bulunamadı.");
        }
    }

    private static void paraCek(String kullaniciAdi) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Çekmek istediğiniz miktarı girin: ");
        double miktar = scanner.nextDouble();

        if (hesaplar.containsKey(kullaniciAdi)) {
            double eskiBakiye = hesaplar.get(kullaniciAdi);
            if (eskiBakiye >= miktar) {
                hesaplar.put(kullaniciAdi, eskiBakiye - miktar);
                if (cekilenParalar.containsKey(kullaniciAdi)) {
                    double eskiCekilen = cekilenParalar.get(kullaniciAdi);
                    cekilenParalar.put(kullaniciAdi, eskiCekilen + miktar);
                } else {
                    cekilenParalar.put(kullaniciAdi, miktar);
                }
                System.out.println(miktar + " TL çekildi. Yeni bakiye: " + (eskiBakiye - miktar) + " TL");
            } else {
                System.out.println("Yetersiz bakiye!");
            }
        } else {
            System.out.println("Hesap bakiyesi bulunamadı.");
        }
    }
}
