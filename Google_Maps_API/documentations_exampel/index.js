// Initialize and add the map
function initMap() {
    // The location of Uluru
    const uluru = { lat: 50.0835625, lng: 14.4354846 };
    // The map, centered at Uluru
    const map = new google.maps.Map(document.getElementById("map"), {
      zoom: 12,
      center: uluru,
      mapId: '22ab788bd54e737',
      type: ["subway_stantion"]
    });
    const contentString = "My info about pinpoint"
    
    const infowindow = new google.maps.InfoWindow({
        content: contentString,
        arialLabel: "Info",
    });
    // The marker, positioned at Uluru
    const marker = new google.maps.Marker({
      position: uluru,
      map: map,
      title: "Info"
    });

    marker.addListener("click", ()=> {
        infowindow.open({
            anchor: marker,
            map,
        });
    });
  }
  
  window.initMap = initMap;