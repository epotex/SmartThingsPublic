

definition(
name: "Soundbar Mute",
namespace: "GCWF2IR",
author: "Epotex",
description: "Control IR",
category: "My Apps",
iconUrl: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience.png",
iconX2Url: "https://s3.amazonaws.com/smartapp-icons/Convenience/Cat-Convenience@2x.png")

preferences {
    section("Choose Virtuall switch that will controll the IR") {
    	input "trigger", "capability.switch", title: "Which switch?", required: true
    }
}

def installed() {
    log.debug "Installed with settings: ${settings}"
    initialize()
}

def updated() {
    log.debug "Updated with settings: ${settings}"
    unsubscribe()
    initialize()
}

def initialize() {
	subscribe(trigger, "switch.on", switchOnHandler)
	subscribe(trigger, "switch.off", switchOffHandler)
}

def switchOnHandler(evt) {
	log.debug "ON"
    def deviceNetworkId = "C0A800FA:1386"   
	def theCom = "	sendir,1:3,1,37993,1,1,171,169,19,19,19,19,19,19,19,19,19,57,19,57,19,19,19,19,19,57,19,57,19,57,19,57,19,19,19,19,19,19,19,19,19,170,19,19,19,19,19,19,19,19,19,57,19,19,19,19,19,19,19,57,19,57,19,57,19,19,19,19,19,57,19,57,19,57,19,19,19,19,19,19,19,57,19,3799"
	sendHubCommand(new physicalgraph.device.HubAction("""$theCom\r\n""",  physicalgraph.device.Protocol.LAN, deviceNetworkId))
}

def switchOffHandler(evt) {
	log.debug "OFF"
    def deviceNetworkId = "C0A800FA:1386"   
	def theCom = "	sendir,1:3,1,37993,1,1,171,169,19,19,19,19,19,19,19,19,19,57,19,57,19,19,19,19,19,57,19,57,19,57,19,57,19,19,19,19,19,19,19,19,19,170,19,19,19,19,19,19,19,19,19,57,19,19,19,19,19,19,19,57,19,57,19,57,19,19,19,19,19,57,19,57,19,57,19,19,19,19,19,19,19,57,19,3799"
	sendHubCommand(new physicalgraph.device.HubAction("""$theCom\r\n""",  physicalgraph.device.Protocol.LAN, deviceNetworkId))
}