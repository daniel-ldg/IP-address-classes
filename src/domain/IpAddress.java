package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Daniel Ladrón de Guevara
 */
public class IpAddress {

    private Integer octet1;
    private Integer octet2;
    private Integer octet3;
    private Integer octet4;

    private AddressClass addressClass;

    public IpAddress(Integer octet1, Integer octet2, Integer octet3, Integer octet4) {
        this.octet1 = octet1;
        this.octet2 = octet2;
        this.octet3 = octet3;
        this.octet4 = octet4;
        calcAddessClass();
    }

    public IpAddress(String ipAddress) {
        String[] octetArray = ipAddress.split("\\.");
        if (octetArray.length != 4) {
            octetArray = "999.999.999.999".split("\\.");
        }
        this.octet1 = Integer.parseInt(octetArray[0]);
        this.octet2 = Integer.parseInt(octetArray[1]);
        this.octet3 = Integer.parseInt(octetArray[2]);
        this.octet4 = Integer.parseInt(octetArray[3]);
        calcAddessClass();
    }

    public Integer getOctet1() {
        return octet1;
    }

    public Integer getOctet2() {
        return octet2;
    }

    public Integer getOctet3() {
        return octet3;
    }

    public Integer getOctet4() {
        return octet4;
    }

    public List<Integer> getOctets() {
        ArrayList<Integer> octets = new ArrayList<>();
        octets.add(octet1);
        octets.add(octet2);
        octets.add(octet3);
        octets.add(octet4);
        return octets;
    }

    public AddressClass getAddressClass() {
        return addressClass;
    }

    private void calcAddessClass() {
        if (octet1 >= 0 && octet1 <= 127) {
            this.addressClass = AddressClass.CLASS_A;
        } else if (octet1 >= 128 && octet1 <= 191) {
            this.addressClass = AddressClass.CLASS_B;
        } else if (octet1 >= 192 && octet1 <= 223) {
            this.addressClass = AddressClass.CLASS_C;
        } else if (octet1 >= 224 && octet1 <= 239) {
            this.addressClass = AddressClass.CLASS_E;
        } else if (octet1 >= 240 && octet1 <= 255) {
            this.addressClass = AddressClass.CLASS_D;
        }
    }

    public boolean isValid() {
        if (octet1 >= 255) {
            return false;
        }
        if (octet2 >= 255) {
            return false;
        }
        if (octet3 >= 255) {
            return false;
        }
        if (octet4 >= 255) {
            return false;
        }
        return true;
    }

    public String getBinaryString() {

        String output = "";

        for (Integer octet : getOctets()) {
            String binary = String.format("%8s", Integer.toBinaryString(octet)).replace(" ", "0");
            output = output.concat(binary).concat(" ");
        }

        return output;

    }

    public IpAddress getNetworkAddress() {
        if (addressClass == AddressClass.CLASS_A) {
            return new IpAddress(octet1, 0, 0, 0);
        } else if (addressClass == AddressClass.CLASS_B) {
            return new IpAddress(octet1, octet2, 0, 0);
        } else if (addressClass == AddressClass.CLASS_C) {
            return new IpAddress(octet1, octet2, octet3, 0);
        } else {
            return null;
        }
    }

    public IpAddress getBroadcastAddress() {
        if (addressClass == AddressClass.CLASS_A) {
            return new IpAddress(octet1, 255, 255, 255);
        } else if (addressClass == AddressClass.CLASS_B) {
            return new IpAddress(octet1, octet2, 255, 255);
        } else if (addressClass == AddressClass.CLASS_C) {
            return new IpAddress(octet1, octet2, octet3, 255);
        } else {
            return null;
        }
    }

    public IpAddress getNetmask() {
        if (addressClass == AddressClass.CLASS_A) {
            return new IpAddress(255, 0, 0, 0);
        } else if (addressClass == AddressClass.CLASS_B) {
            return new IpAddress(255, 255, 0, 0);
        } else if (addressClass == AddressClass.CLASS_C) {
            return new IpAddress(255, 255, 255, 0);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return octet1 + "." + octet2 + "." + octet3 + "." + octet4;
    }
}
