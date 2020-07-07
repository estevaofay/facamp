import { Injectable } from '@angular/core';
import { AngularFireDatabase } from 'angularfire2/database';
import { Campaign } from '../../models/campaign/campaign.model';

@Injectable()
export class DatabaseServiceProvider {
  private campaignDatabase = this.db.list<Campaign>('campaigns');
  constructor(private db : AngularFireDatabase) { }

  getCampaigns() {
    return this.campaignDatabase;
  }

  addCampaign(campaign : Campaign){
    this.campaignDatabase.push(campaign)
  }

  // getAll(){
    
  //   return this.firebase.list('/campaigns/').valueChanges();
  // }

  

  // removeCampaign(id){
  //   this.firebase.list('/campaigns/').remove(id);
  // }

}
