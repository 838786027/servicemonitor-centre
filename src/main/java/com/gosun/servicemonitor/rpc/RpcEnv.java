/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package com.gosun.servicemonitor.rpc;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class RpcEnv extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"RpcEnv\",\"namespace\":\"com.gosun.servicemonitor.rpc\",\"fields\":[{\"name\":\"ip\",\"type\":\"string\"},{\"name\":\"port\",\"type\":\"int\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence ip;
  @Deprecated public int port;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>. 
   */
  public RpcEnv() {}

  /**
   * All-args constructor.
   */
  public RpcEnv(java.lang.CharSequence ip, java.lang.Integer port) {
    this.ip = ip;
    this.port = port;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return ip;
    case 1: return port;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: ip = (java.lang.CharSequence)value$; break;
    case 1: port = (java.lang.Integer)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'ip' field.
   */
  public java.lang.CharSequence getIp() {
    return ip;
  }

  /**
   * Sets the value of the 'ip' field.
   * @param value the value to set.
   */
  public void setIp(java.lang.CharSequence value) {
    this.ip = value;
  }

  /**
   * Gets the value of the 'port' field.
   */
  public java.lang.Integer getPort() {
    return port;
  }

  /**
   * Sets the value of the 'port' field.
   * @param value the value to set.
   */
  public void setPort(java.lang.Integer value) {
    this.port = value;
  }

  /** Creates a new RpcEnv RecordBuilder */
  public static com.gosun.servicemonitor.rpc.RpcEnv.Builder newBuilder() {
    return new com.gosun.servicemonitor.rpc.RpcEnv.Builder();
  }
  
  /** Creates a new RpcEnv RecordBuilder by copying an existing Builder */
  public static com.gosun.servicemonitor.rpc.RpcEnv.Builder newBuilder(com.gosun.servicemonitor.rpc.RpcEnv.Builder other) {
    return new com.gosun.servicemonitor.rpc.RpcEnv.Builder(other);
  }
  
  /** Creates a new RpcEnv RecordBuilder by copying an existing RpcEnv instance */
  public static com.gosun.servicemonitor.rpc.RpcEnv.Builder newBuilder(com.gosun.servicemonitor.rpc.RpcEnv other) {
    return new com.gosun.servicemonitor.rpc.RpcEnv.Builder(other);
  }
  
  /**
   * RecordBuilder for RpcEnv instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<RpcEnv>
    implements org.apache.avro.data.RecordBuilder<RpcEnv> {

    private java.lang.CharSequence ip;
    private int port;

    /** Creates a new Builder */
    private Builder() {
      super(com.gosun.servicemonitor.rpc.RpcEnv.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(com.gosun.servicemonitor.rpc.RpcEnv.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.ip)) {
        this.ip = data().deepCopy(fields()[0].schema(), other.ip);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.port)) {
        this.port = data().deepCopy(fields()[1].schema(), other.port);
        fieldSetFlags()[1] = true;
      }
    }
    
    /** Creates a Builder by copying an existing RpcEnv instance */
    private Builder(com.gosun.servicemonitor.rpc.RpcEnv other) {
            super(com.gosun.servicemonitor.rpc.RpcEnv.SCHEMA$);
      if (isValidValue(fields()[0], other.ip)) {
        this.ip = data().deepCopy(fields()[0].schema(), other.ip);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.port)) {
        this.port = data().deepCopy(fields()[1].schema(), other.port);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'ip' field */
    public java.lang.CharSequence getIp() {
      return ip;
    }
    
    /** Sets the value of the 'ip' field */
    public com.gosun.servicemonitor.rpc.RpcEnv.Builder setIp(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.ip = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'ip' field has been set */
    public boolean hasIp() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'ip' field */
    public com.gosun.servicemonitor.rpc.RpcEnv.Builder clearIp() {
      ip = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'port' field */
    public java.lang.Integer getPort() {
      return port;
    }
    
    /** Sets the value of the 'port' field */
    public com.gosun.servicemonitor.rpc.RpcEnv.Builder setPort(int value) {
      validate(fields()[1], value);
      this.port = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'port' field has been set */
    public boolean hasPort() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'port' field */
    public com.gosun.servicemonitor.rpc.RpcEnv.Builder clearPort() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public RpcEnv build() {
      try {
        RpcEnv record = new RpcEnv();
        record.ip = fieldSetFlags()[0] ? this.ip : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.port = fieldSetFlags()[1] ? this.port : (java.lang.Integer) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}