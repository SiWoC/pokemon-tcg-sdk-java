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
package nl.siwoc.pokemontcg_io_reader.model.tcgplayer;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Prices {
	public Values normal;
	public Values holofoil;
	public Values reverseHolofoil;
	public Values unlimited;
	public Values unlimitedHolofoil;
	
	@JsonProperty("1stEdition")
	public Values firstEdition;

	@JsonProperty("1stEditionHolofoil")
	public Values firstEditionHolofoil;
	
	@JsonProperty("1stEditionNormal")
	public Values firstEditionNormal;
}

