/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.gosun.servicemonitor.rpc;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Info extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Info\",\"namespace\":\"com.gosun.servicemonitor.rpc\",\"fields\":[{\"name\":\"sendTime\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence sendTime;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public Info() {}

  /**
   * All-args constructor.
   */
  public Info(java.lang.CharSequence sendTime) {
    this.sendTime = sendTime;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return sendTime;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: sendTime = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'sendTime' field.
   */
  public java.lang.CharSequence getSendTime() {
    return sendTime;
  }

  /**
   * Sets the value of the 'sendTime' field.
   * @param value the value to set.
   */
  public void setSendTime(java.lang.CharSequence value) {
    this.sendTime = value;
  }

  /** Creates a new Info RecordBuilder */
  public static com.gosun.servicemonitor.rpc.Info.Builder newBuilder() {
    return new com.gosun.servicemonitor.rpc.Info.Builder();
  }
  
  /** Creates a new Info RecordBuilder by copying an existing Builder */
  public static com.gosun.servicemonitor.rpc.Info.Builder newBuilder(com.gosun.servicemonitor.rpc.Info.Builder other) {
    return new com.gosun.servicemonitor.rpc.Info.Builder(other);
  }
  
  /** Creates a new Info RecordBuilder by copying an existing Info instance */
  public static com.gosun.servicemonitor.rpc.Info.Builder newBuilder(com.gosun.servicemonitor.rpc.Info other) {
    return new com.gosun.servicemonitor.rpc.Info.Builder(other);
  }
  
  /**
   * RecordBuilder for Info instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Info>
    implements org.apache.avro.data.RecordBuilder<Info> {

    private java.lang.CharSequence sendTime;

    /** Creates a new Builder */
    private Builder() {
      super(com.gosun.servicemonitor.rpc.Info.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.gosun.servicemonitor.rpc.Info.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.sendTime)) {
        this.sendTime = data().deepCopy(fields()[0].schema(), other.sendTime);
        fieldSetFlags()[0] = true;
      }
    }
    
    /** Creates a Builder by copying an existing Info instance */
    private Builder(com.gosun.servicemonitor.rpc.Info other) {
            super(com.gosun.servicemonitor.rpc.Info.SCHEMA$);
      if (isValidValue(fields()[0], other.sendTime)) {
        this.sendTime = data().deepCopy(fields()[0].schema(), other.sendTime);
        fieldSetFlags()[0] = true;
      }
    }

    /** Gets the value of the 'sendTime' field */
    public java.lang.CharSequence getSendTime() {
      return sendTime;
    }
    
    /** Sets the value of the 'sendTime' field */
    public com.gosun.servicemonitor.rpc.Info.Builder setSendTime(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.sendTime = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'sendTime' field has been set */
    public boolean hasSendTime() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'sendTime' field */
    public com.gosun.servicemonitor.rpc.Info.Builder clearSendTime() {
      sendTime = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public Info build() {
      try {
        Info record = new Info();
        record.sendTime = fieldSetFlags()[0] ? this.sendTime : (java.lang.CharSequence) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
