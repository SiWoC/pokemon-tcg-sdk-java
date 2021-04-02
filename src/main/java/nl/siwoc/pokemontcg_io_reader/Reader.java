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

import nl.siwoc.pokemontcg_io_reader.model.cards.*;
import nl.siwoc.pokemontcg_io_reader.model.common.*;
import nl.siwoc.pokemontcg_io_reader.model.sets.*;
import nl.siwoc.pokemontcg_io_reader.utils.HttpUtils;

public class Reader {

	final static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		System.out.println(getRarities());
		//getSets();
		List<Card> cards = getCardsByQuery("?q=set.id:base1").data;
		System.out.println(cards.size());
		for (Card card : cards) {
			System.out.println(card.id + " " + card.nationalPokedexNumbers + " " + card.name);
		}
	}

	/**
	 * You can call this with a query like "?q=set.id:base1" or "?page=5"
	 * You need to provide the ? questionmark as well
	 * So you can also leave it empty to get all cards (which will return only max 250)
	 * 
	 * @param query
	 * @return CardsData which holds a List<Card> data and pagination info
	 * @throws Exception
	 * 
	 * @see <a href="https://docs.pokemontcg.io/#api_v2cards_list">https://docs.pokemontcg.io/#api_v2cards_list</a>
	 */
	public static CardsData getCardsByQuery(String query) throws Exception {
		CardsData cardsData = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/cards" + query;
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return cardsData;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			cardsData = mapper.readValue(conn.getInputStream(), CardsData.class);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return cardsData;
	}
	
	public static Card getCardById(String id) throws Exception {
		Card card = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/cards/" + id;
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return card;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			CardData cardData = mapper.readValue(conn.getInputStream(), CardData.class);
			card = cardData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return card;
	}

	public static Set getSetById(String id) throws Exception {
		Set set = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/sets/" + id;
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return set;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			SetData setData = mapper.readValue(conn.getInputStream(), SetData.class);
			set = setData.data;
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return set;
	}
	
	/**
	 * You can call this with a query like "?q=legalities.standard:legal" or "?page=2&pageSize=10"
	 * You need to provide the ? questionmark as well
	 * So you can also leave it empty to get all sets (which will fit in the max 250)
	 * 
	 * @param query
	 * @return SetsData which holds a List<Set> data and pagination info
	 * @throws Exception
	 * 
	 * @see <a href="https://docs.pokemontcg.io/#api_v2sets_list">https://docs.pokemontcg.io/#api_v2sets_list</a>
	 */
	public static SetsData getSetsByQuery(String query) throws Exception {
		SetsData setsData = null;
		HttpURLConnection conn = null;
		
		try {
			String url = Constants.BASE_URL + "v2/sets" + query;
			conn = HttpUtils.getConnection(url);
			if (conn.getResponseCode() != 200) {
				if (conn.getResponseCode() == 404) {
					return setsData;
				}
				throw new Exception("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			setsData = mapper.readValue(conn.getInputStream(), SetsData.class);
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return setsData;
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
