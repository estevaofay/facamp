import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';
import { AddCampaignPage } from '../add-campaign/add-campaign';
import { SocialSharing } from '@ionic-native/social-sharing';
import { Campaign } from '../../models/campaign/campaign.model';

import { AngularFireList } from 'angularfire2/database';
import { DatabaseServiceProvider } from '../../providers/database-service/database-service';

import { Observable } from 'rxjs/Observable';

@IonicPage()
@Component({
  selector: 'page-campaigns',
  templateUrl: 'campaigns.html',
})
export class CampaignsPage {

  campaigns : Observable<{}[]>;
  campaign : Campaign;
  
  constructor(public firebase : DatabaseServiceProvider,public navCtrl: NavController, public navParams: NavParams, private socialSharing : SocialSharing) {
    
            console.log(this.campaigns);
  }

  // addCampaign(cpg : Campaign){
  //   this.firebase.addCampaign(cpg);
  // }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CampaignsPage');
  }

  goToAddCampaign(){
    this.navCtrl.push(AddCampaignPage.name);
  }

  shareCampaign() {
    this.socialSharing.canShareVia('WhatsApp').then(() => {
      this.socialSharing.share('Join my RPG Campaign', 'Your invitation awaits!',null,'http://rpg-companion.io/join/AEOPB/')
    }).catch(() =>{
      this.socialSharing.shareViaSMS('http://rpg-companion.io/join/AEOPB/', '5511996211488');
    });
  }

}

