package id.ac.ui.cs.mobileprogramming.farras.pokecarddemo.model;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {PokemonCard.class, PokemonSet.class}, version = 1, exportSchema = false)
public abstract class PokemonRoomDatabase extends RoomDatabase {
    private static PokemonRoomDatabase INSTANCE;

    public static PokemonRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PokemonRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PokemonRoomDatabase.class, "pokemon_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract PokemonCardDao pokemonCardDao();

    public abstract PokemonSetDao pokemonSetDao();
}

