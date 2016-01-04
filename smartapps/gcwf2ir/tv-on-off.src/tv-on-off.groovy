

definition(
name: "TV On/Off",
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

def deviceNetworkId = "C0A800FA:1386"   
def theCom = "sendir,1:3,1,38109,1,1,342,170,21,22,21,22,21,64,21,22,21,22,21,22,21,22,21,22,21,64,21,64,21,22,21,64,21,64,21,64,21,64,21,64,21,22,21,22,21,22,21,64,21,22,21,22,21,22,21,22,21,64,21,64,21,64,21,22,21,64,21,64,21,64,21,64,21,1525,342,85,21,3650,342,85,21,3650,342,85,21,3810"

def switchOnHandler(evt) {
	log.debug "ON"
     sendHubCommand(new physicalgraph.device.HubAction("""$theCom\r\n""",  physicalgraph.device.Protocol.LAN, deviceNetworkId))
}

def switchOffHandler(evt) {
	log.debug "OFF"
     sendHubCommand(new physicalgraph.device.HubAction("""$theCom\r\n""",  physicalgraph.device.Protocol.LAN, deviceNetworkId))
}