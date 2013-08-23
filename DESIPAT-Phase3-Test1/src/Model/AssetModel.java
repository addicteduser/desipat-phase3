/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 * 
 * @author Aram
 */

public class AssetModel {

	private static AssetModel assetModel;

	private AssetModel() {

	}

	public static AssetModel getInstance() {
		if (assetModel == null)
			assetModel = new AssetModel();

		return assetModel;
	}

	private String Type;
	private String schedule;
	private String Classification;
	private String Confidentiality;
	private String Integrity;
	private String Availability;
	private String Assetowner;
	private String Assetcustodian;
	private String Assetname;
	private String Storagelocation;
	private int Financial;
	private Date dateAcquired;
	private Date retentionPeriodFrom;
	private Date retentionPeriodTo;

	public String getType() {
		return Type;
	}

	public void setType(String Type) {
		this.Type = Type;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getClassification() {
		return Classification;
	}

	public void setClassification(String Classification) {
		this.Classification = Classification;
	}

	public String getConfidentiality() {
		return Confidentiality;
	}

	public void setConfidentiality(String Confidentiality) {
		this.Confidentiality = Confidentiality;
	}

	public String getIntegrity() {
		return Integrity;
	}

	public void setIntegrity(String Integrity) {
		this.Integrity = Integrity;
	}

	public String getAvailability() {
		return Availability;
	}

	public void setAvailability(String Availability) {
		this.Availability = Availability;
	}

	public String getAssetowner() {
		return Assetowner;
	}

	public void setAssetowner(String Assetowner) {
		this.Assetowner = Assetowner;
	}

	public String getAssetcustodian() {
		return Assetcustodian;
	}

	public void setAssetcustodian(String Assetcustodian) {
		this.Assetcustodian = Assetcustodian;
	}

	public String getAssetname() {
		return Assetname;
	}

	public void setAssetname(String Assetname) {
		this.Assetname = Assetname;
	}

	public String getStoragelocation() {
		return Storagelocation;
	}

	public void setStoragelocation(String Storagelocation) {
		this.Storagelocation = Storagelocation;
	}

	public int getFinancial() {
		return Financial;
	}

	public void setFinancial(int Financial) {
		this.Financial = Financial;
	}

	public Date getDateAcquired() {
		return dateAcquired;
	}

	public void setDateAcquired(Date dateAcquired) {
		this.dateAcquired = dateAcquired;
	}

	public Date getRetentionPeriodFrom() {
		return retentionPeriodFrom;
	}

	public void setRetentionPeriodFrom(Date retentionPeriodFrom) {
		this.retentionPeriodFrom = retentionPeriodFrom;
	}

	public Date getRetentionPeriodTo() {
		return retentionPeriodTo;
	}

	public void setRetentionPeriodTo(Date retentionPeriodTo) {
		this.retentionPeriodTo = retentionPeriodTo;
	}
}
