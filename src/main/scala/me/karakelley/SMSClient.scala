package me.karakelley

import com.twilio.Twilio
import com.twilio.`type`.PhoneNumber
import com.twilio.rest.api.v2010.account.Message
import com.typesafe.config.Config

class SMSClient(config: Config) {

  private val ACCOUNT_SID = config.getString("twilio.account_sid")
  private val AUTH_TOKEN = config.getString("twilio.auth_token")

  Twilio.init(ACCOUNT_SID, AUTH_TOKEN)

  private val from = new PhoneNumber(config.getString("twilio.from_number"))
  private val to = new PhoneNumber(config.getString("twilio.to_number"))

  def send(wod: Wod): Message = {
    Message.creator(to, from, wod.toString).create()
  }
}

