public class Magija {
    private int snaga;
    private String ime;

    public Magija(int snaga, String ime){
        this.snaga = snaga;
        this.ime = ime;
    }

    public Magija(Magija m){
        this.ime = m.ime;
        this.snaga = m.snaga;
    }

    public int getSnaga() {
        return snaga;
    }

    public String getIme() {
        return ime;
    }

    public void setSnaga(int snaga) {
        this.snaga = snaga;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return "Ime magije je: " + ime + " sa snagom: " + snaga;
    }
}
