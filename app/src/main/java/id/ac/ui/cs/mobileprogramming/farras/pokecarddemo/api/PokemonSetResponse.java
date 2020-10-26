package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.api;

import java.util.List;

public class PokemonSetResponse {
    private List<PokemonSet> sets;

    public PokemonSetResponse(List<PokemonSet> sets) {
        this.sets = sets;
    }

    public List<PokemonSet> getSets() {
        return sets;
    }

    public void setSets(List<PokemonSet> sets) {
        this.sets = sets;
    }
}
