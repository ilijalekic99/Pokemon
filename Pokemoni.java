public abstract class Pokemoni implements Comparable{
    private String ime;
    private int nivo;
    private Magija magija1;
    private Magija magija2;
    private int zivot;
    private Magija poslednjaMagija = null;

    public Pokemoni(String ime, int nivo, Magija magija1, Magija magija2){
        this.ime = ime;
        this.nivo = nivo;
        this.magija1 = magija1;
        this.magija2 = magija2;
        this.zivot = 100;
    }

    public String getIme() {
        return ime;
    }

    public int getNivo() {
        return nivo;
    }

    public Magija getMagija1() {
        return magija1;
    }

    public Magija getMagija2() {
        return magija2;
    }

    public int getZivot() {
        return zivot;
    }

    public void nanesiStetu(int kolikoStete){
        this.zivot -= kolikoStete;
    }

    public abstract void baciMagiju(Pokemoni neprijatelj);

    public void setPoslednjaMagija(Magija poslednjaMagija) {
        this.poslednjaMagija = poslednjaMagija;
    }

    public Magija getPoslednjaMagija() {
        return poslednjaMagija;
    }

    @Override
    public String toString() {
        return "[" + nivo + "] " + ime;
    }

    //@Override
    public int compareTo(Pokemoni o) {
        if(this instanceof igracPokemon && o instanceof CpuPokemon){
            return -1;
        }
        else if(this instanceof CpuPokemon && o instanceof igracPokemon){
            return 1;
        }
        else if(this instanceof igracPokemon && o instanceof  igracPokemon){
            return Integer.compare(o.getNivo(), this.getNivo());
        }
        else if(this instanceof CpuPokemon && o instanceof CpuPokemon){
            return Integer.compare(o.getNivo(), this.getNivo());
        }
        else{
            return Integer.compare(o.getNivo(), this.getNivo());
        }
    }
}
