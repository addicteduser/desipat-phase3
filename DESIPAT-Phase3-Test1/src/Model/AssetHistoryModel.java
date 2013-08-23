/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 * 
 * @author sgtan_
 */
public class AssetHistoryModel {

	private static AssetHistoryModel assetHistoryModel;

	public AssetHistoryModel() {

	}

	public static AssetHistoryModel getInstance() {
		if (assetHistoryModel == null)
			assetHistoryModel = new AssetHistoryModel();

		return assetHistoryModel;
	}

	private String assetName;
	private int identifier;
	private String assetOwner;
	private Date dateAcquired;

	public String getAssetName() {
		return this.assetName;
	}

	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	public int getIdentifier() {
		return this.identifier;
	}

	public void setIdentifier(int identifier) {
		this.identifier = identifier;
	}

	public String getAssetOwner() {
		return this.assetOwner;
	}

	public void setAssetOwner(String assetOwner) {
		this.assetOwner = assetOwner;
	}

	public Date getDateAcquired() {
		return dateAcquired;
	}

	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

}
