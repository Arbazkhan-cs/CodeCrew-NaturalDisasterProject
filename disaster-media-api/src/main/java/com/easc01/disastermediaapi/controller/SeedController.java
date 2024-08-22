package com.easc01.disastermediaapi.controller;

import com.easc01.disastermediaapi.constant.AppConstant;
import com.easc01.disastermediaapi.model.Disaster;
import com.easc01.disastermediaapi.model.Video;
import com.easc01.disastermediaapi.repository.DisasterRepository;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Slf4j
@Hidden
@RestController
@RequiredArgsConstructor
@RequestMapping(path = AppConstant.SEED)
public class SeedController<T> {

    private final DisasterRepository disasterRepository;


    @PostMapping(value = "/disasters")
    public T addDisaster() {
        try {
            Disaster disaster0 = Disaster.builder()
                    .title("Earthquake struck Japan")
                    .summary("A 7.8 magnitude earthquake struck off the coast of Japan near Sendai this afternoon, causing widespread disruption. Buildings shook, power outages were reported, and a tsunami warning was issued for coastal areas. Emergency services are responding, and the full extent of the damage is still unclear. The government has urged residents to remain vigilant as aftershocks are expected.")
                    .recordId("japan_earthquake_150824")
                    .incidentLocation("japan")
                    .incidentType("earthquake")
                    .build();

            Disaster disaster1 = Disaster.builder()
                    .title("Hurricane Hits Florida")
                    .summary("Hurricane Emilia made landfall in Florida with winds reaching 150 mph, causing significant damage along the coast. Thousands of homes were destroyed, and over a million people were left without power. Emergency services are working around the clock to rescue stranded residents and provide aid to those affected.")
                    .recordId("florida_hurricane_160824")
                    .incidentLocation("florida")
                    .incidentType("hurricane")
                    .build();

            Disaster disaster2 = Disaster.builder()
                    .title("Massive Wildfire in California")
                    .summary("A massive wildfire erupted in Northern California, scorching thousands of acres of forest and forcing the evacuation of several communities. The fire, fueled by strong winds and dry conditions, continues to spread rapidly. Firefighters are battling the blaze, but containment efforts are being hampered by difficult terrain.")
                    .recordId("california_wildfire_170824")
                    .incidentLocation("california")
                    .incidentType("wildfire")
                    .build();

            Disaster disaster3 = Disaster.builder()
                    .title("Severe Flooding in India")
                    .summary("Monsoon rains have caused severe flooding in the state of Kerala, India, submerging entire villages and displacing thousands of people. Rescue operations are underway as authorities attempt to reach those trapped by rising waters. The government has issued warnings of more heavy rainfall in the coming days.")
                    .recordId("india_flooding_180824")
                    .incidentLocation("kerala")
                    .incidentType("flooding")
                    .build();

            Disaster disaster4 = Disaster.builder()
                    .title("Tornado Rips Through Oklahoma")
                    .summary("A powerful tornado tore through the outskirts of Oklahoma City, leveling homes and leaving a trail of destruction in its wake. Emergency services are on the scene, and search and rescue operations are ongoing. The tornado has been classified as an EF4, with winds reaching up to 200 mph.")
                    .recordId("oklahoma_tornado_190824")
                    .incidentLocation("oklahoma")
                    .incidentType("tornado")
                    .build();

            Disaster disaster5 = Disaster.builder()
                    .title("Volcanic Eruption in Indonesia")
                    .summary("Mount Sinabung in Indonesia erupted violently, spewing ash and lava into the air and forcing the evacuation of nearby villages. The eruption, which was accompanied by a series of smaller tremors, has prompted authorities to raise the alert level and advise people to stay away from the area.")
                    .recordId("indonesia_volcano_200824")
                    .incidentLocation("sumatra")
                    .incidentType("volcanic eruption")
                    .build();

            Set<Video> videos0 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster0)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster0)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster0)
                            .build()
            );

            Set<Video> videos1 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster1)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster1)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster1)
                            .build()
            );

            Set<Video> videos2 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster2)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster2)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster2)
                            .build()
            );

            Set<Video> videos3 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster3)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster3)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster3)
                            .build()
            );

            Set<Video> videos4 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster4)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster4)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster4)
                            .build()
            );

            Set<Video> videos5 = Set.of(
                    Video.builder()
                            .title("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G")
                            .url("https://www.youtube.com/watch?v=LLe9Bic7WUw")
                            .description("Japan Earthquake 2024 LIVE | Powerful Earthquake Hits Off Southern Japan, Tsunami Alert On | N18G" +
                                    "A powerful earthquake struck off Japan’s southern coast on August 8, triggering a tsunami advisory. Residents were urged to stay away from the coastline, but there were no immediate reports of injuries or serious damage." +
                                    "#Japan #japanearthquake #news18live" +
                                    "n18oc_live" +
                                    "News18 Mobile App - https://onelink.to/desc-youtube")
                            .publishedDate(Instant.now())
                            .userId("eyr82u")
                            .disaster(disaster5)
                            .build(),

                    Video.builder()
                            .title("Japan Earthquake: Why Japan issued its first-ever 'megaquake advisory' | WION Pulse")
                            .url("https://www.youtube.com/watch?v=FCL2K1GX_U4")
                            .description("Japan has issued its first-ever advisory on higher-than-usual risks of a megaquake after an earthquake of magnitude 7.1 jolted on Thursday at the edge of a tremulous seabed zone along the Pacific coast known as the Nankai Trough. A possible Nankai megaquake and tsunami disaster could kill hundreds of thousands of people and cause a trillion-dollar damage to Japan." +
                                    "#japan #japanearthquake #trendingnews " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("qioddnwio")
                            .disaster(disaster5)
                            .build(),

                    Video.builder()
                            .title("Japan trembles under the shadow of Megaquake | Latest News | WION")
                            .url("https://www.youtube.com/watch?v=ddN-23IKbwA")
                            .description("Megaquake alert in Japan. Experts say 70% chance of a magnitude 8 or 9 striking. Japan's meteorological agency maintains Megaquake advisory. Watch in for more details." +
                                    "#japanearthquake #megaquake #climatechange " +
                                    "About Channel: " +
                                    "WION The World is One News examines global issues with in-depth analysis. We provide much more than the news of the day. Our aim is to empower people to explore their world. With our Global headquarters in New Delhi, we bring you news on the hour, by the hour. We deliver information that is not biased. We are journalists who are neutral to the core and non-partisan when it comes to world politics. People are tired of biased reportage and we stand for a globalized united world. So for us, the World is truly One." +
                                    "Please keep discussions on this channel clean and respectful and refrain from using racist or sexist slurs and personal insults." +
                                    "Subscribe to our channel at https://goo.gl/JfY3NI" +
                                    "Check out our website: http://www.wionews.com" +
                                    "Join our WhatsApp Channel: https://bit.ly/455YOQ0" +
                                    "Connect with us on our social media handles:" +
                                    "Facebook:   / wionews  " +
                                    "Twitter:   / wionews  " +
                                    "Follow us on Google News for the latest updates" +
                                    "Zee News:- https://bit.ly/2Ac5G60" +
                                    "Zee Business:- https://bit.ly/36vI2xa" +
                                    "DNA India:- https://bit.ly/2ZDuLRY" +
                                    "WION: https://bit.ly/3gnDb5J" +
                                    "Zee News Apps: https://bit.ly/ZeeNewsApps")
                            .publishedDate(Instant.now())
                            .userId("behbfeb")
                            .disaster(disaster5)
                            .build()
            );


            disaster0.setVideos(videos0);
            disaster1.setVideos(videos1);
            disaster2.setVideos(videos2);
            disaster3.setVideos(videos3);
            disaster4.setVideos(videos4);
            disaster5.setVideos(videos5);

            return (T) disasterRepository.saveAll(
                    List.of(
                            disaster0,
                            disaster1,
                            disaster2,
                            disaster3,
                            disaster4,
                            disaster5
                    )
            );

        } catch (Exception e) {
            log.error(e.getMessage());
            return (T) e.getMessage();
        }
    }


}
