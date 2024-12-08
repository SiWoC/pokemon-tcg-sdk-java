/*******************************************************************************
 * Copyright (c) 2019-2021 Niek Knijnenburg
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/
package nl.siwoc.pokemontcg_io_reader.model.cards; 

import java.util.List;

import nl.siwoc.pokemontcg_io_reader.model.cardmarket.CardMarket;
import nl.siwoc.pokemontcg_io_reader.model.common.Legalities;
import nl.siwoc.pokemontcg_io_reader.model.sets.Set;
import nl.siwoc.pokemontcg_io_reader.model.tcgplayer.TCGPlayer; 

public class Card{
    public String id;
    public String name;
    public String supertype;
    public List<String> subtypes;
    public String level;
    public String hp;
    public List<String> types;
    public String evolvesFrom;
    public List<String> evolvesTo;
    public List<String> rules;
    public AncientTrait ancientTrait;
    public List<Ability> abilities;
    public List<Attack> attacks;
    public List<Weakness> weaknesses;
    public List<Resistance> resistances;
    public List<String> retreatCost;
    public int convertedRetreatCost;
    public Set set;
    public String number;
    public String artist;
    public String rarity;
    public String flavorText;
    public List<Integer> nationalPokedexNumbers;
    public Legalities legalities;
    public String regulationMark;
    public Images images;
    public TCGPlayer tcgplayer;
    public CardMarket cardmarket;
}
