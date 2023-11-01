package a3;

class CookieJar {
    private int cookieQTY;
    private int maxCookieQTY;
    CookieJar(int cookies, int maxCookieQTY) {
        this.cookieQTY = cookies;
        this.maxCookieQTY = maxCookieQTY;
        System.out.println(toString());
    }
    private int getCookieQTY() {return cookieQTY;}
    private void setCookieQTY(int cookies) {this.cookieQTY = cookies;}

    public synchronized void takeCookie(String threadName) {
        if (getCookieQTY() == 0) {
            System.out.println(threadName+" cannot take cookies from an empty jar"+toString());
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            takeCookie(threadName);
        }
        else {
            setCookieQTY(getCookieQTY()-1);
            System.out.println(threadName+" has taken a cookie from the jar"+toString());
        }
        notify();
    }

    public synchronized void fillJar(String threadName) {
        if (getCookieQTY() != 0) {
            System.out.println(threadName+" cannot fill the jar until its empty"+toString());
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            fillJar(threadName);
        }
        else {
            setCookieQTY(this.maxCookieQTY);
            System.out.println(threadName+" has filled the jar up to "+this.maxCookieQTY+toString());
        }
        notify();
    }

    public String toString() {
        return " -> Cookies in jar: "+this.cookieQTY;
    }
}

class FamilyMember implements Runnable {
    private int id;
    private boolean adult;
    private CookieJar cookieJar;

    private String name;

    FamilyMember(int id, boolean adult, CookieJar cookieJar) {
        this.adult = adult;
        this.id = id;
        if (this.adult) this.name = "adult nº"+this.id;
        else this.name = "kid nº"+id;
        this.cookieJar = cookieJar;
    }
    public void run() {
        if (adult) cookieJar.fillJar(name);
        else cookieJar.takeCookie(name);
    }
}
public class PotDeGaletes {
    public static void main(String[] args) {
        int maxCookieQTY = 10;
        int cookies = 5;
        int adultQTY = 2;
        int kidQTY = 20;
        CookieJar cookieJar = new CookieJar(cookies, maxCookieQTY);

        for (int i = 0; i < adultQTY; i++) new Thread(new FamilyMember(i+1, true, cookieJar)).start();

        for (int i = 0; i < kidQTY; i++) new Thread(new FamilyMember(i+1, false, cookieJar)).start();

    }
}
