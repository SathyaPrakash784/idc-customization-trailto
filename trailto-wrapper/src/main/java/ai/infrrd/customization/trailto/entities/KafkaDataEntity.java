package ai.infrrd.customization.trailto.entities;

import java.util.Map;


public class KafkaDataEntity {

	private String userId;
	private String scanRequestId;
	private Map<String, Object> fields;
	private Map<String, Object> lineitems;
	private Map<String, Object> wrapperFields;
	private Map<String, Object> wrapperLineitems;
	private Long uploadDateTime;
	private Long lastModifyDateTime;
	private String extractionScore;
	private String status;
	private String s3ImagePath;
	private String profile;
	private int licenseConsumed;
	private String rawText;
	private String fileType;
	private String process;

	public KafkaDataEntity( String userId, String scanRequestId, Map<String, Object> wrapperFields,
			Map<String, Object> wrapperLineitems, Long uploadDateTime, Long lastModifyDateTime, String profile,
			String s3ImagePath) {
		this.userId = userId;
		this.scanRequestId = scanRequestId;
		this.wrapperFields = wrapperFields;
		this.uploadDateTime = uploadDateTime;
		this.lastModifyDateTime = lastModifyDateTime;
		this.wrapperLineitems = wrapperLineitems;
		this.profile = profile;
		this.s3ImagePath = s3ImagePath;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId( String userId) {
		this.userId = userId;
	}

	public String getScanRequestId() {
		return scanRequestId;
	}

	public void setScanRequestId( String scanRequestId) {
		this.scanRequestId = scanRequestId;
	}

	public Map<String, Object> getFields() {
		return fields;
	}

	public void setFields( Map<String, Object> fields) {
		this.fields = fields;
	}

	public Map<String, Object> getLineitems() {
		return lineitems;
	}

	public void setLineitems( Map<String, Object> lineitems) {
		this.lineitems = lineitems;
	}

	public Long getUploadDateTime() {
		return uploadDateTime;
	}

	public void setUploadDateTime( Long uploadDateTime) {
		this.uploadDateTime = uploadDateTime;
	}

	public Long getLastModifyDateTime() {
		return lastModifyDateTime;
	}

	public void setLastModifyDateTime( Long lastModifyDateTime) {
		this.lastModifyDateTime = lastModifyDateTime;
	}

	public String getExtractionScore() {
		return extractionScore;
	}

	public void setExtractionScore( String extractionScore) {
		this.extractionScore = extractionScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus( String status) {
		this.status = status;
	}

	public String getS3ImagePath() {
		return s3ImagePath;
	}

	public void setS3ImagePath( String s3ImagePath) {
		this.s3ImagePath = s3ImagePath;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile( String profile) {
		this.profile = profile;
	}

	public int getLicenseConsumed() {
		return licenseConsumed;
	}

	public void setLicenseConsumed(int licenseConsumed) {
		this.licenseConsumed = licenseConsumed;
	}

	public String getRawText() {
		return rawText;
	}

	public void setRawText( String rawText) {
		this.rawText = rawText;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType( String fileType) {
		this.fileType = fileType;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess( String process) {
		this.process = process;
	}

	public Map<String, Object> getWrapperFields() {
		return wrapperFields;
	}

	public void setWrapperFields( Map<String, Object> wrapperFields) {
		this.wrapperFields = wrapperFields;
	}

	public Map<String, Object> getWrapperLineitems() {
		return wrapperLineitems;
	}

	public void setWrapperLineitems( Map<String, Object> wrapperLineitems) {
		this.wrapperLineitems = wrapperLineitems;
	}

}
