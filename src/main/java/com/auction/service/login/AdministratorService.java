package com.auction.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auction.model.DashBoard;
import com.auction.model.category.Category;
import com.auction.model.login.Administrator;
import com.auction.model.login.BoTokenAuth;
import com.auction.model.tabBord.MoneyAuction;
import com.auction.repository.ClientRepository;
import com.auction.repository.category.CategoryRepository;
import com.auction.repository.login.AdministratorRepository;
import com.auction.repository.login.BoTokenAuthRepo;
import com.auction.repository.tableauBord.MoneyAuctionRepo;

import java.util.Date;
import java.util.List;

@Service
public class AdministratorService {
    @Autowired
    LoginService ls;

    @Autowired
    AdministratorRepository ar;

    @Autowired
    ClientRepository cr;

    @Autowired
    BoTokenAuthRepo bt;

    @Autowired
    MoneyAuctionRepo moneyAuctionRepo;

    @Autowired
    CategoryRepository categoryRepository;

    public BoTokenAuth generateToken(Administrator admin) {
        String token = ls.generateToken();
        Date expiration = new Date();
        int date = expiration.getDate() + 2;
        expiration.setDate(date);
        BoTokenAuth ba = new BoTokenAuth();
        ba.setAdminId(admin.getId());
        ba.setToken(token);
        ba.setExpirationDate(expiration);
        bt.save(ba);

        return ba;
    }

    public Administrator getConnected(String email, String password) {
        return ar.findByEmailAndPwd(email, password);
    }

    public void insertAdministrator(Administrator admin) {
        ar.save(admin);
    }

    public void updateAdministrator(Integer id, Administrator admin) {
        admin.setId(id);
        ar.save(admin);
    }

    public void deleteAdministrator(Integer id) {
        ar.deleteById(id);
    }

    public void deleteClient(Integer id) {
        cr.deleteById(id);
    }

    public List<Category> toMoneyAuctionCat(List<MoneyAuction> moneyAuctions) {
        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            category.setDetails(null);
            for (MoneyAuction moneyAuction : moneyAuctions) {
                moneyAuction.getAuction().setAuctionBetList(null);
                moneyAuction.getAuction().setProductPictureList(null);
                if (moneyAuction.getAuction().getCategoryDetails().getCategoryId() == category.getId()) {
                    category.setTotalComission(category.getTotalComission() + moneyAuction.getAmountComission());
                }
            }
        }
        return categories;
    }

    public DashBoard getDashboard() {
        DashBoard dashboard = new DashBoard();
        dashboard.setMoneyAuctions(moneyAuctionRepo.findAll());
        dashboard.setMoneyAuctionCats(toMoneyAuctionCat(dashboard.getMoneyAuctions()));
        dashboard.setTotalComission(
                dashboard.getMoneyAuctions().stream().mapToDouble(MoneyAuction::getAmountComission).sum());
        return dashboard;
    }
}
