package persistenceDAO;

import java.util.List;

import Model.Address;

public interface AddressDAO {
	public void save(Address address);  // Create
	public Address findByPrimaryKey(String street, int zipcode, String username);     // Retrieve
	public List<Address> findAll();     
	public List<Address> findMyAddresses(String user);
	public void update(Address address); //Update
	public void delete(Address address); //Delete	
	public void deleteAllOfUser(String user);
}
