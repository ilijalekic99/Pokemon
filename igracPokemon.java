public class igracPokemon extends Pokemoni{

    private int izabranaMagija;

    public igracPokemon(String ime, int nivo, Magija magija1, Magija magija2) {
        super(ime, nivo, magija1, magija2);
        this.izabranaMagija = 1;
    }

    @Override
    public void baciMagiju(Pokemoni neprijatelj) {
        if(izabranaMagija == 1){
            neprijatelj.nanesiStetu(getMagija1().getSnaga());
            setPoslednjaMagija(getMagija1());
        }
        else{
            neprijatelj.nanesiStetu(getMagija2().getSnaga());
            setPoslednjaMagija(getMagija2());
        }
    }

    public int getIzabranaMagija() {
        return izabranaMagija;
    }

    public void setIzabranaMagija(int i) {
        if(i == 1){
            this.izabranaMagija = 1;
        }
        else if(i == 2){
            this.izabranaMagija = 2;
        }
        else{ return; }
    }

    @Override
    public String toString() {
        return "[p]" + super.toString();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
