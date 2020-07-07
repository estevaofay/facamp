import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { Campaign } from '../../models/campaign/campaign.model';
import { DatabaseServiceProvider } from '../../providers/database-service/database-service';
import { CampaignsPage } from '../campaigns/campaigns';

@IonicPage()
@Component({
  selector: 'page-add-campaign',
  templateUrl: 'add-campaign.html',
})
export class AddCampaignPage {
  campaign : Campaign = {
    name: '',
    id: 'QWERTY',
    ruleBase : '',
    rpg: 'tormenta',
    inicialLevel: undefined,
    inventorySize : undefined
  }

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public dbService : DatabaseServiceProvider) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AddCampaignPage');
  }

  addCampaign(campaign : Campaign) {
    this.dbService.addCampaign(campaign);
    this.navCtrl.setRoot(CampaignsPage.name)
  }

}
