package persistenceDAO;

import java.util.List;

import Model.PaymentMethod;

public interface PaymentMethodDAO {
	public void save(PaymentMethod paymentMethod);  // Create
	public PaymentMethod findByPrimaryKey(String CardNumber, String User);     // Retrieve
	public List<PaymentMethod> findAll();   	
	public List<PaymentMethod> findAll(String user);       
	public void update(PaymentMethod paymentMethod); //Update
	public void delete(PaymentMethod paymentMethod); //Delete
	public void deleteAllOfUser(String user);
}
