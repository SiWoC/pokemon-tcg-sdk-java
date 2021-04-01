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
package nl.siwoc.pokemontcg_io_reader;

import java.net.HttpURLConnection;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import nl.siwoc.pokemontcg_io_reader.model.cards.Card;
import nl.siwoc.pokemontcg_io_reader.model.cards.CardData;
import nl.siwoc.pokemontcg_io_reader.model.common.SimpleData;
import nl.siwoc.pokemontcg_io_reader.model.sets.Set;
import nl.siwoc.pokemontcg_io_reader.model.sets.SetData;
import nl.siwoc.pokemontcg_io_reader.utils.HttpUtils;

public class Reader {

	final static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		System.out.println(getRarities());
		//getSets();
		List<Card> cards = getCardsByQuery("?q=set.id:base1");
		System.out.println(cards.size());
		for (Card card : cards) {
			System.out.println(card.id + " " + card.nationalPokedexNumbers + " " + card.name);
			if (card.id.equals("base1-8")) {
				System.out.println(card.id + "found");
			}
			if (card.tcgplayer != null && card.tcgplayer.prices != null) {
				if (card.tcgplayer.prices.firstEditionHolofoil != null
						|| card.tcgplayer.prices.firstEditionNormal != null) {
					System.out.println(card.id + "filled");
				}
			}
		}
	}

	public static List<Card> getCardsByQuery(String query) throws Exception {
		List<Card> cards = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/cards" + query;
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return cards;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			CardData cardData = mapper.readValue(conn.getInputStream(), CardData.class);
			cards = cardData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return cards;
	}
	
	public static List<Set> getSets() throws Exception {
		List<Set> sets = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/sets";
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return sets;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SetData setData = mapper.readValue(conn.getInputStream(), SetData.class);
			sets = setData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return sets;
	}

	public static List<String> getTypes() throws Exception {
		List<String> types = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/types";
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return types;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SimpleData simpleData = mapper.readValue(conn.getInputStream(), SimpleData.class);
			types = simpleData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return types;
	}

	public static List<String> getSubTypes() throws Exception {
		List<String> subTypes = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/subtypes";
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return subTypes;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SimpleData simpleData = mapper.readValue(conn.getInputStream(), SimpleData.class);
			subTypes = simpleData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return subTypes;
	}

	public static List<String> getSuperTypes() throws Exception {
		List<String> superTypes = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/supertypes";
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return superTypes;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SimpleData simpleData = mapper.readValue(conn.getInputStream(), SimpleData.class);
			superTypes = simpleData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return superTypes;
	}

	public static List<String> getRarities() throws Exception {
		List<String> rarities = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/rarities";
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return rarities;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SimpleData simpleData = mapper.readValue(conn.getInputStream(), SimpleData.class);
			rarities = simpleData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return rarities;
	}

}
