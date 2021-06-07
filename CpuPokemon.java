import java.util.Random;

public class CpuPokemon extends Pokemoni{
    private static Random random = new Random();

    public CpuPokemon(String ime, int nivo, Magija magija1, Magija magija2) {
        super(ime, nivo, magija1, magija2);
    }

    @Override
    public void baciMagiju(Pokemoni neprijatelj) {
        Double r = random.nextDouble();
        if(r >= 0.5){
            neprijatelj.nanesiStetu(getMagija2().getSnaga());
            setPoslednjaMagija(getMagija2());
        }
        else{
            neprijatelj.nanesiStetu(getMagija1().getSnaga());
            setPoslednjaMagija(getMagija1());
        }
    }

    @Override
    public String toString() {
        return "[ai] " + super.toString();
    }
}
